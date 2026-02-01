import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/**
 * A probabilistic data structure for membership testing.
 * Designed to provide O(k) insertion and lookup time with O(m) space complexity.
 */
public class CustomBloomFilter {
    private final BitSet bitSet;
    private final int bitSetSize; //m
    private final int numHashFunctions; //k

    /**
     * @param n Expected number of elements (e.g., 10,000)
     * @param p Desired false positive probability (e.g., 0.01 for 1%)
     */
    public CustomBloomFilter(int n, double p) {
        bitSetSize = (int) Math.ceil(-(n * Math.log(p)) / (Math.pow(Math.log(2), 2)));
        numHashFunctions = (int) Math.max(1, Math.round((double) bitSetSize / n * Math.log(2)));
        bitSet = new BitSet(bitSetSize);
    }

    /**
     * Inserts an element into the filter by setting k bits to 1.
     */
    public void add(String element) {
        for (int i = 0; i < numHashFunctions; i++) {
            //use 'i' as a seed to simulate k different hash functions
            int hash = Hashing.murmur3_32_fixed(i)
                    .hashString(element, StandardCharsets.UTF_8)
                    .asInt();

            int index = Math.abs(hash % bitSetSize);
            bitSet.set(index);
        }
    }

    /**
     * Checks if an element might be in the set.
     * Returns false if definitely not present, true if possibly present.
     */
    public boolean mightContain(String element) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = Hashing.murmur3_32_fixed(i)
                    .hashString(element, StandardCharsets.UTF_8)
                    .asInt();

            int index = Math.abs(hash % bitSetSize);
            if (!bitSet.get(index)) {
                return false; // definitely not in the set
            }
        }
        return true; // might be in the set
    }
}
