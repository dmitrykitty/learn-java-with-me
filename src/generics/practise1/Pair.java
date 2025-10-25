package generics.practise1;

public class Pair <K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
