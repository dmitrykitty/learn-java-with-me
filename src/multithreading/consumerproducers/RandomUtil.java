package multithreading.consumerproducers;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private RandomUtil() {
    }

    public static int getRandomNum(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static int getRandomDelay(int min, int max) {
        return getRandomNum(min, max + 1);
    }

}
