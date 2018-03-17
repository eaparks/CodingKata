package com.pox

import spock.lang.Specification

class BloomFilterTest extends Specification {

    def "word should or should not be in dictionary"() {

        given:
        def bloomFilter = new BloomFilter()
        bloomFilter.add("Hello")
        bloomFilter.add("cat")

        expect:
        bloomFilter.isInDictionary(word) == inDictionary

        where:
        word    | inDictionary
        "Hello" | true
        "asdf"  | false
        "cat"   | true
        "doog"  | false
    }

    def "bitset should be NON-empty after adding a work to dictionary"() {

        given:
        def bloomFilter = new BloomFilter()
        bloomFilter.add("cat")
        bloomFilter.add("dog")

        expect:
        bloomFilter.numberOfBitsSet() > 5

    }
}
