package generics.practise1;

public class PairUtil {
    private PairUtil() {}

    public static<K, V> Pair<V, K> swap(Pair<K, V> source){
        K key = source.getKey();
        V value = source.getValue();
        return new Pair<>(value, key);

    }
}
