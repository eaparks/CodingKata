package com.pox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {

    private Set<String> words;
    private BloomFilter bloomFilter;

    public Dictionary(File rawWords) {

        init(rawWords);
    }

    private void init(File rawWords) {

        words = new HashSet<>(20);
        bloomFilter = new BloomFilter();
        loadWordsFromFile(rawWords);
    }

    private void loadWordsFromFile(File rawWordsFile) {

        if (rawWordsFile != null && rawWordsFile.canRead()) {
            try {
                Scanner scanner = new Scanner(rawWordsFile);
                String word;
                while (scanner.hasNext()) {
                    word = scanner.next();
                    if (word.length() > 0) {
                        words.add(word);
                        bloomFilter.add(word);
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("File wasn't found.");
                System.out.println("rawWords = " + rawWordsFile);
            }
        } else {
            System.err.println("Can't read dictionary file!");
        }
    }

    public int size() {

        return words.size();
    }
}
