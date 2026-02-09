package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapTheory {
    static void main() {
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); //max heap

        Queue<Integer> maxHeap2 = new PriorityQueue<>((x, y) -> y - x);

        //dodawanie elementow
        minHeap.add(40);
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(20);

        // 2. Podgląd szczytu - O(1)
        // Zwraca 10, ponieważ to najmniejsza wartość
        System.out.println("Szczyt (peek): " + minHeap.peek());

        // 3. Usuwanie szczytu - O(log n)
        // Usuwa 10 i reorganizuje kopiec (sift-down)
        int removedElement = minHeap.poll();
        System.out.println("Usunięto: " + removedElement);
    }

}
