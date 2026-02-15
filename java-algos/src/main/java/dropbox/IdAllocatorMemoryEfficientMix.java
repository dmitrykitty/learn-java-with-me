package dropbox;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class IdAllocatorMemoryEfficient {
    private final BitSet allocated;
    private final Queue<Integer> realisedIds;
    private final int maxVal;
    private int nextId;

    public IdAllocatorMemoryEfficient(int maxVal) {
        this.maxVal = maxVal;
        allocated = new BitSet(maxVal);
        realisedIds = new ArrayDeque<>(maxVal);
        nextId = 0;
    }

    public int allocate() {
        int id;
        if(!realisedIds.isEmpty()) {
            id = realisedIds.poll();
        } else if (nextId < maxVal) {
            id = nextId++;

        } else {
            return -1;
        }
        allocated.set(id);
        return id;
    }

    public void release(int id) {
        if(!allocated.get(id)) {
            throw new IllegalStateException("Allocated id " + id + " not found");
        }

        allocated.set(id);
        realisedIds.add(id);
    }
}
