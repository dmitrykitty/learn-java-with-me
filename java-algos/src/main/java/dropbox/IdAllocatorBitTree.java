package dropbox;

import java.util.BitSet;

public class IdAllocatorBitTree {
    private final int capacity;
    private final int beforeLeaves;
    private final BitSet bitHeap;

    public IdAllocatorBitTree(int maxValue) {
        //get 2^(n-1) and * 2 where 2^n >= maxValue
        int min2nNum = maxValue == 1 ? 1 : Integer.highestOneBit(maxValue - 1) << 1;
        beforeLeaves = min2nNum - 1; //to get first 2^n number >= maxValue
        bitHeap = new BitSet(2 * min2nNum - 1);
        capacity = maxValue;

        for(int i = capacity; i < min2nNum; i++) {
            updateTree(i + beforeLeaves, true);
        }
    }

    //allocate first free index
    public int allocate() {
        if (bitHeap.get(0)) {
            return -1;
        }

        int bitIndex = findFirstFreeChild();
        int id = bitIndex - beforeLeaves;

        updateTree(bitIndex, true);
        return id;
    }

    //free allocated index
    public void release(int id) {
        if(id < 0 ||  id >= capacity) {
            throw new IllegalArgumentException("Id out of range");
        }
        int bitIndex = id + beforeLeaves;
        if (!bitHeap.get(bitIndex)) {
            throw new IllegalStateException("Id " + id + " not allocated yet");
        }
        updateTree(bitIndex, false);
    }


    private int findFirstFreeChild() {
        int currentBitIndex = 0;
        while (currentBitIndex < beforeLeaves) {
            int left = currentBitIndex * 2 + 1;
            int right = currentBitIndex * 2 + 2;

            if (!bitHeap.get(left)) {
                currentBitIndex = left;
            } else {
                currentBitIndex = right;
            }
        }
        return currentBitIndex;
    }

    private void updateTree(int bitIndex, boolean allocating) {
        bitHeap.set(bitIndex, allocating);
        int currentBitIndex = bitIndex;
        while (currentBitIndex != 0) {
            int parentBitIndex = (currentBitIndex - 1) / 2;
            int leftBitIndex = parentBitIndex * 2 + 1;
            int rightBitIndex = parentBitIndex * 2 + 2;

            boolean newParentState = bitHeap.get(leftBitIndex) && bitHeap.get(rightBitIndex);
            if(newParentState == bitHeap.get(parentBitIndex)) {
                break;
            }
            bitHeap.set(parentBitIndex, newParentState);
            currentBitIndex = parentBitIndex;
        }
    }
}

