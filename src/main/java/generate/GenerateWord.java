package generate;


import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateWord implements PasswordGenerator {

    private static final String NUMBERS = "1234567890";

    @SneakyThrows
    private static List<String> allWords() {
        List<String> linesFile = new ArrayList<>();
        File dictionary = Paths.get(GenerateWord.class.getResource("words_alpha.txt").toURI()).toFile(); //TODO refactor to use Resources
        try (FileReader fileReader = new FileReader(dictionary)) {
            linesFile = IOUtils.readLines(fileReader); //TODO close Reader
        }
        return linesFile;
    }

    @Override
    public String generate(GenerateRules rules) throws FileNotFoundException {
        if ((!rules.isNumeric()) && (maxLength() * rules.getWordcount() < rules.getLength())) {
            return "В словнику не знайдено таких довгих слів";
        } else {
            return generatePassword(rules.getLength(), rules.getWordcount(), rules.getSpecial(), rules.isNumeric(),
                    rules.isToUpperFirst());
        }
    }

    private String generatePassword(int length, int wordcount, char special, boolean numeric, boolean toUpperFirst) {
        //TODO try to simplify
        StringBuilder result = new StringBuilder();
        int reserve = 0;
        if ((length - wordcount * 4 + 1) <= 1 || (length - wordcount * 3) <= 1) {
            reserve = 1;
        }
        if (numeric) {
            if (special != 0) {
                reserve = RandomUtils.nextInt(1, length - wordcount * 4 + 1);
            } else {
                reserve = RandomUtils.nextInt(1, length - wordcount * 3);
            }
        } else {
            if (special != 0) {
                reserve = length - wordcount * 4 - 1;
            }
        }
        int newLength = length - reserve;
        int freePlace;
        int wordlength;
        while (wordcount > 1) {
            freePlace = newLength - result.length();
            if ((freePlace - wordcount * 3) <= 3) {
                wordlength = 3;
            } else {
                wordlength = RandomUtils.nextInt(3, freePlace - wordcount * 3);
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
            result.append(NUMBERS.charAt(RandomUtils.nextInt(0, NUMBERS.length())));
            reserve--;
        }
        return result.toString();
    }

    public String toUpperFirst(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
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

