package org.example;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        final String LETTERS = "_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
       // System.out.println(LETTERS.length());
        int length=7;
        StringBuilder password = new StringBuilder();
        for (int i=1; i<length+1; i++) {

            Random random = new Random();
            password.append(LETTERS.charAt(random.nextInt(length) ));
        }

       // System.out.println(password);

        File dictionary = new File("words_alpha.txt");
        try {
            List<String> lines = IOUtils.readLines(new FileReader(dictionary));
            List<String> words = new ArrayList<>();
            int n=29;
            for (String line: lines) {
                if (line.length()==n) {
                words.add(line);


                }}





            Random random = new Random();
            int numberword = random.nextInt(words.size());

          System.out.println(words.get(numberword));
            int maxlength=0;

            for (String line: lines) {

                if (line.length() > maxlength) {
                    maxlength=line.length();
                }
                }
            System.out.println(maxlength);



        }
        catch (IOException e) {
            e.printStackTrace();
        }




        }
    }

