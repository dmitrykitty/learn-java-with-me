package lrucash;

public class LRURunner {
    static void main() {
        LT146_LRUCache lru = new LT146_LRUCache(2);

        lru.put(2, 6);
        lru.get(1);
        lru.put(1, 5);
        lru.put(1, 2);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));


        LRUCache lru2 = new LRUCache(2);
        lru2.put(1,1);
        lru2.put(2,2);
        System.out.println(lru2.get(1));
        lru2.put(3,3);
        System.out.println(lru2.get(2));
        lru2.put(4,4);
        System.out.println(lru2.get(1));
        System.out.println(lru2.get(3));
        System.out.println(lru2.get(4));
    }
}
