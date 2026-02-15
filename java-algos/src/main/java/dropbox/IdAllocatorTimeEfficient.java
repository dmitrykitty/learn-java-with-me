package dropbox;

import java.util.*;

public class IdAllocatorTimeEfficient {
    private int nextId;
    private final int maxVal;
    private final Queue<Integer> releasedId;
    private final Set<Integer> allocated;

    public IdAllocatorTimeEfficient(int maxVal) {
        this.maxVal = maxVal;
        this.releasedId = new ArrayDeque<>();
        this.allocated = new HashSet<>();
        this.nextId = 0;
    }

    public int allocate() {
        int id;
        if(!releasedId.isEmpty()) {
            id = releasedId.poll();
        } else if(nextId < maxVal) {
            id = nextId++;
        } else {
            return -1;
        }
        allocated.add(id);
        return id;
    }

    public void release(int id) {
        if(id < 0 || id >= maxVal || !allocated.contains(id)) {
            throw new RuntimeException(String.format("Current id %d doesn't allocated", id));
        }

        releasedId.add(id);
        allocated.remove(id);
    }
}
