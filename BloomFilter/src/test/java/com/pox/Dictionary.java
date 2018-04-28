package com.pox;

import java.io.File;

public class Dictionary {

    public Dictionary(File rawWords) {

        init(rawWords);
    }

    private void init(File rawWords) {

        loadWordsFromFile(rawWords);
    }

    public void loadWordsFromFile(File rawWords) {

        if(rawWords.canRead()) {

            // open file

            // read word

            // move to next line

            // read word.....


        } else {
            System.err.println("Can't read dictionary file!");
        }

    }
}
