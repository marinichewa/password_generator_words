package org.example.controler;

import org.example.generate.GenerateRules;
import org.example.generate.GenerateWord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleControllerTest {

    @Test
    void runTest() {

        GenerateWord word = new GenerateWord();
        GenerateRules rules=new GenerateRules(6,0,'p',false,true);
        assertEquals ("Minimal count of word must be 1",word.generate(rules));

    }
}