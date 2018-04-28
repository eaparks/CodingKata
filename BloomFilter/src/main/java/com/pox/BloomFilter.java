package com.pox;

import java.util.BitSet;

/**
 * Although there is a certain probability of error,
 * Bloom filters never produce false negatives.
 * If it says the word isn't in the set, it definitely isn't in the set.
 */
public class BloomFilter {

    // These 2 are only for the reference hashCode method below
    private int hash;
    private char[] value = {'a', 'b', 'c'};

    private int bitSetSize = 1000 * 1000;
    private BitSet bitSet = new BitSet(bitSetSize);

    int hashCountPerWord = 3;

    public boolean isInDictionary(String word) {

        // create hashes
        int hash_1 = hashCode1(31, word.toCharArray());
        int hash_2 = hashCode1(37, word.toCharArray());
        int hash_3 = hashCode1(51, word.toCharArray());
        // TODO - test for uniqueness?

        return bitSet.get(hash_1) && bitSet.get(hash_2) && bitSet.get(hash_3);
    }

    public void add(String newWord) {

        // create hashes
        int hash_1 = hashCode1(31, newWord.toCharArray());
        int hash_2 = hashCode1(37, newWord.toCharArray());
        int hash_3 = hashCode1(51, newWord.toCharArray());

        // add hashes to bitset
        bitSet.flip(hash_1);
        bitSet.flip(hash_2);
        bitSet.flip(hash_3);
    }

    /**
     * For reference.
     * This is the String hashCode function from the JDK.
     */
    public int hashCode() {

        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

    private int hashCode1(int seed, char[] charsInString) {

        int h = 0;
        if (charsInString.length > 0) {

            for (int i = 0; i < charsInString.length; i++) {
                h = seed * h + charsInString[i];
            }
        }
        return h;
    }

    public int numberOfBitsSet() {

        int bitSetString = bitSet.cardinality();

        return bitSetString;
    }
}
