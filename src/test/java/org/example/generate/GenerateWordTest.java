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
        GenerateRules rules=new GenerateRules(30,1,'p',false,true);
        GenerateWord word = new GenerateWord();
        assertEquals("Pool", word.toUpperFirst(rules.isToUpperFirst(),"pool"));
        GenerateRules rules2=new GenerateRules(30,1,'p',false,false);
        GenerateWord word2 = new GenerateWord();
        assertEquals("pool", word.toUpperFirst(rules2.isToUpperFirst(),"pool"));

    }

}