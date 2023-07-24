package org.example;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateWord  {

    private static final String NUMBERS = "1234567890";
    public String generate(GenerateRules rules) {


    }

    public StringBuilder peekChar (int length, int wordcount, char special, boolean numeric, boolean toLower) throws FileNotFoundException {
    if (!numeric) {
                if (special == 0) {
                    if (!toLower) {

                        StringBuilder result=new StringBuilder();
                            while (result.length()==length) {
                            int wordlength=new Random ().nextInt(length-wordcount+1);
                            result.append(randomwWord(wordlength)).toString();}
                                return result;
                        }


                    }


                }


                return null;

    }

    private String passwordJustWords (int length, int wordcount, char special, boolean numeric, boolean toUpperFirst)
            throws FileNotFoundException {

        StringBuilder result = new StringBuilder();
        int countnumeric=0;
        int newLength;
        if (numeric) {
            if (special!=0) {
                countnumeric=new Random().nextInt (1,length-wordcount*4);
            }
            else {countnumeric=new Random().nextInt (1,length-wordcount*3);
            }
        }
        int newLengthnum=length-countnumeric;
        while (wordcount>1) {
            newLength=newLengthnum-result.length();
            int wordlength = new Random().nextInt(3,newLength-(wordcount-1)*3);
            wordcount--;
            if (!toUpperFirst) {
                result.append(randomWord(wordlength));}
            else {result.append(toUpperFirst(randomWord(wordlength)));}
            if (special!=0) {
                result.append(special);
            }
        }
        if (!toUpperFirst) {
            result.append(randomWord(newLengthnum-result.length()));
        }
        else {
            result.append(toUpperFirst(randomWord(newLengthnum-result.length())));
        }
        while (countnumeric>0) {
            result.append(NUMBERS.charAt(new Random().nextInt(NUMBERS.length()))) ;
            countnumeric--;
        }
        return result.toString();}






    public String toUpperFirst (String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public  String randomWord (int length) throws FileNotFoundException {
            List<String> words = new ArrayList<>();
            for (String line : allWords()) {
                if (line.length() == length) {
                words.add(line);
                }
            }
            int numberword = new Random().nextInt(words.size());
            return words.get(numberword);
    }
    public  String randomwWord () throws FileNotFoundException {
        int numberword = new Random().nextInt(allWords().size());
        return allWords().get(numberword);
    }

    public  int maxlength () throws FileNotFoundException {
        int maxlength=0;
        for (String line: allWords()) {
            if (line.length() > maxlength) {
                maxlength=line.length();
            }
        }
        return maxlength;
    }
    public  int minlength () throws FileNotFoundException {
        int minlength=0;
        for (String line: allWords()) {
            if (line.length() < minlength) {
                minlength=line.length();
            }
        }
        return minlength;
    }

    private static List<String> allWords () throws FileNotFoundException {
        List<String> linesFile=new ArrayList<>();
        try {
            File dictionary = new File("words_alpha.txt");
            linesFile = IOUtils.readLines(new FileReader(dictionary));}
        catch (IOException e) {
            e.printStackTrace();
        }
        return linesFile;}

}
