package com.dnikitin.jdbc.util.poolconnection;

import com.dnikitin.jdbc.util.PropertiesUtil;
import com.dnikitin.jdbc.util.singleconnection.ConnectionManager;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Manages a pool of database connections to optimize resource usage and performance.
 * <p>
 * Instead of creating a new physical connection for every request, which is expensive
 * in terms of CPU and I/O, this manager "borrows" existing connections from a pool.
 * It uses a Dynamic Proxy to override the {@link Connection#close()} method,
 * returning the connection to the pool instead of physically closing it.
 */
public final class PoolManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "db.pool.size";

    private static final int DEFAULT_POOL_SIZE = 10;

    /** Queue of available proxy connections ready to be used. */
    private static BlockingQueue<Connection> pool;

    /** List of original, physical connections used for final cleanup. */
    private static List<Connection> sourceConnections;

    private PoolManager() {
        // Utility class, no instantiation allowed.
    }

    static {
        initConnectionPool();
    }

    /**
     * Initializes the connection pool by reading properties and creating proxy instances.
     * <p>
     * Each connection is wrapped in a {@link java.lang.reflect.Proxy}.
     * When the application calls {@code connection.close()}, the proxy intercepts the call
     * and adds the connection back to the queue.
     */
    private static void initConnectionPool() {
        String sizeProperty = PropertiesUtil.getProperty(POOL_SIZE_KEY);
        int poolSize = sizeProperty == null ? DEFAULT_POOL_SIZE : Integer.parseInt(sizeProperty);

        pool = new ArrayBlockingQueue<>(poolSize);
        sourceConnections = new ArrayList<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = openConnection();

            // Creating a Dynamic Proxy to handle connection pooling logic.
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(
                    ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> {
                        // If 'close()' is called, return proxy to the pool instead of closing the physical connection.
                        if (method.getName().equals("close")) {
                            return pool.add((Connection) proxy);
                        }
                        // For any other method, execute it on the real physical connection.
                        return method.invoke(connection, args);
                    });

            pool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    /**
     * Retrieves a connection from the pool.
     * This method blocks if no connections are currently available.
     * * @return a {@link Connection} proxy from the pool.
     * @throws RuntimeException if the thread is interrupted while waiting for a connection.
     */
    public static Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status.
            throw new RuntimeException("Error retrieving connection from pool.", e);
        }
    }

    /**
     * Physically closes all connections in the pool.
     * <p>
     * Note: This method iterates over 'sourceConnections' to ensure
     * physical closure, as calling close() on proxies only returns them to the queue.
     */
    public static void closePool() {
        for (Connection connection : sourceConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Logging or handling individual connection closure errors.
                throw new RuntimeException("Error closing physical connection.", e);
            }
        }
    }

    /**
     * Opens a new physical connection to the database using driver properties.
     * * @return a new {@link Connection} instance.
     * @throws RuntimeException if database access fails.
     */
    private static Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getProperty(URL_KEY),
                    PropertiesUtil.getProperty(USERNAME_KEY),
                    PropertiesUtil.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to database.", e);
        }
    }
}