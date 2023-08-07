package org.example.generate;


import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenerateWord implements PasswordGenerator {

    private static final String NUMBERS = "1234567890";

    @SneakyThrows
    private static List<String> allWords() {
        List<String> linesFile = new ArrayList<>();
        File dictionary;
        dictionary = Paths.get(Objects.requireNonNull(GenerateWord.class.getResource("/words_alpha.txt")).toURI()).toFile();
        try (FileReader fileReader = new FileReader(dictionary)) {
            linesFile = IOUtils.readLines(fileReader);
        }
        return linesFile;
    }

    @Override
    public String generate(GenerateRules rules) throws FileNotFoundException {
        if ((!rules.isNumeric()) && (maxLength() * rules.getWordcount() < rules.getLength())) {
            return "In dictionary not found words with this length";
        } else {
            return generatePassword(rules.getLength(), rules.getWordcount(), rules.getSpecial(), rules.isNumeric(),
                    rules.isToUpperFirst());
        }
    }

    private String generatePassword(int length, int wordcount, char special, boolean numeric, boolean toUpperFirst) {
        StringBuilder result = new StringBuilder();
                int reserve = 0;
        if (numeric && special != 0) {
            reserve = RandomUtils.nextInt(wordcount, length - wordcount * 3 + 1);
        }
        if (numeric && special == 0) {
            reserve = RandomUtils.nextInt(1, length - wordcount * 3 + 1);
        }
        if (!numeric && special != 0) {
            reserve = wordcount - 1;
        }
        int newLength = length - reserve;
        while (wordcount > 1) {
            int freePlace = newLength - result.length();
            int wordlength = RandomUtils.nextInt(3, freePlace - (wordcount - 1) * 3 + 1);
            result.append(toUpperFirst(toUpperFirst, randomWord(wordlength)));
            if (special != 0) {
                result.append(special);
                reserve--;
                newLength++;
            }
            wordcount--;
        }
        result.append(toUpperFirst(toUpperFirst, randomWord(newLength - result.length())));
        while (length > result.length()) {
            result.append(NUMBERS.charAt(RandomUtils.nextInt(0, NUMBERS.length())));
        }
        return result.toString();
    }

    public String toUpperFirst(boolean toUpp, String word) {
        if (!toUpp) {
            return word;
        } else {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
    }


    public String randomWord(int length) {
        List<String> words = new ArrayList<>();
        for (String line : allWords()) {
            if ((line.length() == length) && (line.length() > 2)) {
                words.add(line);
            }
        }
        int numberword = RandomUtils.nextInt(0, words.size());
        return words.get(numberword);
    }

    public int maxLength() {
        int maxlength = 0;
        for (String line : allWords()) {
            if (line.length() > maxlength) {
                maxlength = line.length();
            }
        }
        return maxlength;
    }

}

