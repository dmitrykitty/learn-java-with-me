### 1. Model BIO (Blocking I/O - Tradycyjny)

To rozwiązanie najbardziej zbliżone do mojego obecnego kodu, ale w najczystszej postaci "jeden wątek na jedno połączenie".

**Serwer:**
Używamy `FixedThreadPool`. Wątek jest blokowany na operacjach `read()` i `write()`.

```java
// W konstruktorze
this.pool = Executors.newFixedThreadPool(poolSize);

// W pętli głównej
Socket socket = server.accept();
pool.execute(() -> processSocket(socket)); // Wątek z puli jest zajęty aż do zamknięcia socketu

```

**Klient (Synchroniczny):**
Klient wysyła zapytanie i czeka (blokuje się), aż otrzyma odpowiedź.

```java
HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString()); 
// Wątek klienta stoi w miejscu, dopóki serwer nie odpowie
System.out.println(response.body());

```

---

### 2. Model IO + Asynchroniczność (CompletableFuture)

Tutaj serwer pozostaje taki sam (BIO), ale klient zmienia podejście, aby nie marnować zasobów na oczekiwanie.

**Serwer:** Bez zmian (jak wyżej).

**Klient (Asynchroniczny):**
Wykorzystujemy `sendAsync`, co pozwala wysłać wiele zapytań bez blokowania wątku głównego.

```java
httpClient.sendAsync(request, BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(body -> {
        // Ta logika wykona się w wątku z puli HttpClienta, gdy dane dotrą
        System.out.println("Otrzymano body: " + body);
    });
// Wątek główny może w tym czasie robić coś innego

```

---

### 3. Model Virtual Threads (Project Loom - Java 21+)

To jest najnowocześniejsze podejście. Kod wygląda jak w punkcie 1, ale wydajność zbliża się do punktu 4.

**Serwer:**
Zmieniamy tylko sposób tworzenia egzekutora. Nie ograniczamy już liczby wątków, bo są one "tanie".

```java
// Zamiast FixedThreadPool
this.pool = Executors.newVirtualThreadPerTaskExecutor();

// Reszta kodu bez zmian. processSocket(socket) nadal używa blokujących strumieni, 
// ale JVM "odmontuje" wątek wirtualny, gdy ten będzie czekał na I/O.

```

---

### 4. Model Java NIO (Non-blocking I/O - Selector Model)

To całkowita zmiana paradygmatu. Nie używamy `InputStream` ani `OutputStream`. Używamy kanałów (`Channels`) i buforów (`ByteBuffers`).

**Serwer (NIO):**
To rozwiązanie jest najbardziej skomplikowane, ale pozwala jednemu wątkowi obsługiwać tysiące gniazd.

```java
// Inicjalizacja Selektora i Kanału
Selector selector = Selector.open();
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.bind(new InetSocketAddress(PORT));
serverChannel.configureBlocking(false); // Kluczowe: tryb nieblokujący
serverChannel.register(selector, SelectionKey.OP_ACCEPT);

while (running) {
    selector.select(); // Czeka, aż na którymś gnieździe pojawi się zdarzenie
    Set<SelectionKey> keys = selector.selectedKeys();
    Iterator<SelectionKey> iter = keys.iterator();

    while (iter.hasNext()) {
        SelectionKey key = iter.next();
        if (key.isAcceptable()) {
            // Obsługa nowego połączenia
            SocketChannel client = serverChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            // Czytanie danych do ByteBuffer bez blokowania wątku
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            client.read(buffer);
            // Logika przetwarzania...
        }
        iter.remove();
    }
}

```

---

### Porównanie techniczne tych rozwiązań

| Cecha                      | 1. BIO              | 2. IO + Async           | 3. Virtual Threads      | 4. Java NIO                 |
|----------------------------|---------------------|-------------------------|-------------------------|-----------------------------|
| **Główna jednostka**       | Wątek Systemowy     | Callback / Future       | Wątek Wirtualny         | Event / Buffer              |
| **Gdzie jest "Czekanie"?** | Wątek śpi (Kernel)  | Wątek jest wolny        | Wątek wirtualny parkuje | Wątek monitoruje Selector   |
| **Zarządzanie pamięcią**   | Ciężkie (1MB/wątek) | Zależy od implementacji | Bardzo lekkie (Heap)    | Najlżejsze (Direct Buffers) |
| **Trudność kodu**          | Bardzo prosta       | Średnia (łańcuchy)      | Bardzo prosta           | Bardzo wysoka               |

---

### Summary

Tomcat w swoich wczesnych wersjach używał modelu **1 (BIO)**. Szybko jednak okazało się, że przy dużym ruchu serwer "puchnie" od liczby wątków. Dlatego w wersji 6 wprowadzono obsługę modelu **4 (NIO)**. Obecnie Tomcat domyślnie używa NIO, co pozwala mu obsługiwać tysiące połączeń przy relatywnie małej puli wątków roboczych.

W najnowszych wersjach Tomcata (11+) pojawia się wsparcie dla modelu **3 (Virtual Threads)**, co pozwala programistom pisać proste serwlety (model BIO), zachowując skalowalność modelu NIO.
