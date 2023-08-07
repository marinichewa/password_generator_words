package org.example.generate;

import static org.junit.jupiter.api.Assertions.*;

class GenerateWordTest {

    @org.junit.jupiter.api.Test
    void testGenerate() {
        GenerateRules rules=new GenerateRules(30,1,'p',false,true);
        GenerateWord word = new GenerateWord();
        assertEquals("In dictionary not found words with this length", word.generate(rules));

        }

    @org.junit.jupiter.api.Test
    void testToUpperFirst() {
    }

    @org.junit.jupiter.api.Test
    void testRandomWord() {
    }

    @org.junit.jupiter.api.Test
    void testMaxLength() {
    }
}