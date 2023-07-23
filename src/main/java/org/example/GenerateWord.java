package org.example;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class GenerateWord  {

    public static List<String> allWords () throws FileNotFoundException {
    List<String> linesFile=new ArrayList<>();
    try {
        File dictionary = new File("words_alpha.txt");
        linesFile = IOUtils.readLines(new FileReader(dictionary));}
    catch (IOException e) {
        e.printStackTrace();
    }
    return linesFile;}

    public static int maxlength (List<String> lines) {
    int maxlength=0;
        for (String line: lines) {
            if (line.length() > maxlength) {
                maxlength=line.length();
            }
        }
        return maxlength;
    }

    public static List<String> WordsWithLength (List<String> lines, int length) {
        List<String> words = new ArrayList<>();
        for (String line : lines) {
            if (line.length() == length) {
                words.add(line);
            }
        }
        return words;
    }


}
