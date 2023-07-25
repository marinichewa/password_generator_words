package generate;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomUtils;

public class GenerateWord implements PasswordGenerator  {

    @Override
    public String generate (GenerateRules rules) throws FileNotFoundException {
        if ((!rules.isNumeric()) && (maxlength()*rules.getWordcount()< rules.getLength())) {
        return "В словнику не знайдено таких довгих слів"
        }
        else {
    String password=GeneratePassword(rules.getLength(),rules.getWordcount(),rules.getSpecial(),rules.isNumeric(),
            rules.isToUpperFirst());
    return password;
    }
    }



    private static final String NUMBERS = "1234567890";
    private String GeneratePassword (int length, int wordcount, char special, boolean numeric, boolean toUpperFirst)
            throws FileNotFoundException {

        StringBuilder result = new StringBuilder();
        int reserve=0;
        if ((length - wordcount * 4 + 1)<=1 || (length - wordcount * 3)<=1) {
            reserve=1;
        }
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
            int freePlace, wordlength;
            while (wordcount > 1) {
                freePlace = newLength - result.length();
                if ((freePlace - wordcount*3)<=3) {
                    wordlength=3;
                } else
                {wordlength = new RandomUtils().nextInt(3, freePlace - wordcount*3);
                }
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
            File dictionary = new File("src\\main\\java\\generate\\words_alpha.txt");
            linesFile = IOUtils.readLines(new FileReader(dictionary));}
        catch (IOException e) {
            e.printStackTrace();
        }
        return linesFile;}

}

