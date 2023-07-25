package org.example;


import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomUtils;

public class GenerateWord implements PasswordGenerator  {

    private static final String NUMBERS = "1234567890";

    @Override
    public String generate (GenerateRules rules) throws FileNotFoundException {
        if ((!rules.isNumeric()) && (maxlength()*rules.getWordcount()< rules.getLength())) {
            String info= "В словнику не знайдено таких довгих слів";
            return info;
        }
        else {
    String password=GeneratePassword(rules.getLength(),rules.getWordcount(),rules.getSpecial(),rules.isNumeric(),
            rules.isToUpperFirst());
    return password;
    }}

    private String GeneratePassword (int length, int wordcount, char special, boolean numeric, boolean toUpperFirst)
            throws FileNotFoundException {

        StringBuilder result = new StringBuilder();
 //       int chek=length-wordcount*3;
 //       if (numeric) {
 //           chek--;
 //       }
 //       if (special != 0) {
 //           chek=chek-wordcount+1;
 //       }
        int reserve=0;
        if ((length - wordcount * 4 + 1)<=1 || (length - wordcount * 3)<=1) {
            reserve=1;
        }
//        if (chek>0) {
            if (numeric) {
                if (special != 0) {
                    reserve = new RandomUtils().nextInt(1, length - wordcount * 4 + 1);
                } else {
                    reserve = new RandomUtils().nextInt(1, length - wordcount * 3);
                }
            } else {
                if (special != 0) {
                reserve = length - wordcount*4-1;
            }
            }
            int newLength = length - reserve;

            int freePlace;
            int wordlength;
            while (wordcount > 1) {
                freePlace = newLength - result.length();
                if ((freePlace - wordcount*3)<=3) {wordlength=3;} else
                {wordlength = new RandomUtils().nextInt(3, freePlace - wordcount*3);}
                wordcount--;
                if (!toUpperFirst) {
                    result.append(randomWord(wordlength));
                } else {
                    result.append(toUpperFirst(randomWord(wordlength)));
                }
                if (special != 0) {
                    result.append(special);
                                                        }
            }
            if (!toUpperFirst) {
                result.append(randomWord(newLength - result.length()));
            } else {
                result.append(toUpperFirst(randomWord(newLength - result.length())));
            }
 //          }
//       else {
//           if (numeric) {
//               if (special != 0) {
//                   reserve = 1;
//               }
//               while (wordcount > 1) {
//                   if (!toUpperFirst) {
//                       result.append(randomWord(3));}
//                    else {
//                       result.append(toUpperFirst(randomWord(3)));
//                   }
//                    if (special != 0) {
//                       result.append(special);
//                   }
//                   wordcount--;
//               }
//               if (!toUpperFirst) {
//                   result.append(randomWord(3));
//               } else {
//                   result.append(toUpperFirst(randomWord(3)));
//               }

//       }
//       }
        while (reserve > 0) {
            result.append(NUMBERS.charAt(new Random().nextInt(NUMBERS.length())));
            reserve--;
        }
        return result.toString();}

    public String toUpperFirst (String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }


    public  String randomWord (int length) throws FileNotFoundException {
            List<String> words = new ArrayList<>();
            for (String line : allWords()) {
                if ((line.length() == length) && (line.length()>2)) {
                words.add(line);
                }
            }
            int numberword = new Random().nextInt(words.size());
            return words.get(numberword);
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

