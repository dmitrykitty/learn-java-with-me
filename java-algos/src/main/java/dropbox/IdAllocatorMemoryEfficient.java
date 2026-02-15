package dropbox;

import java.util.BitSet;

public class IdAllocatorMemoryEfficient {
    public final BitSet allocated;
    public final int maxValue;

    public IdAllocatorMemoryEfficient(int maxValue) {
        allocated = new BitSet(maxValue);
        this.maxValue = maxValue;
    }

    //TimeComplexty O(N), Memory O(N/8)
    //bitSet.length return index of last allocated bit + 1
    public int allocate() {
        int id = allocated.nextClearBit(0); //return first non true bit.
        // Faster then for loop because check each 8 bits in one
        if(id < maxValue){
            allocated.set(id);
            return id;
        }
        return -1;
    }

    public void release(int bitIndex) {
        if (bitIndex < 0 ||  bitIndex >= maxValue || !allocated.get(bitIndex)) {
            throw new IndexOutOfBoundsException("Illegal Id: " + bitIndex);
        }
        allocated.clear(bitIndex);
    }
}
