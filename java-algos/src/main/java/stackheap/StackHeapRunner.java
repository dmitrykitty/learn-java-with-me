package stackheap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StackHeapRunner {
    static void main() {
        //FIFO QUEUE

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        Integer remove = queue.remove(); //throw NoSuchElementException
        Integer poll = queue.poll(); //returns null if no element found

        Integer element = queue.element(); //throw NoSuchElementException
        Integer peek = queue.peek(); // //returns null if no element found

        String collect = Stream.of(remove, poll, element, peek)
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        System.out.println(collect);

        //STACK LIFO
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.addFirst(2);
        stack.push(3);

        Integer pop = stack.pop();//remove from the top of stack
        Integer peek1 = stack.peek(); //get without removing


    }
}
