package com.pox

import spock.lang.Specification

class DictionaryTest extends Specification {
    def "test loadWordsFromFile"() {
        given:
            File wordFile = new File("scowl-2017.08.24/misc/profane.1")

        when:
        // TODO implement stimulus
            Dictionary dictionary = new Dictionary(wordFile)
        then:
            assert(dictionary.length() > 0)
        // TODO implement assertions
    }
}
