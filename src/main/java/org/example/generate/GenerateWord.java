package org.example.generate;

import lombok.SneakyThrows;
import lombok.val;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import static java.nio.file.Files.lines;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class GenerateWord implements PasswordGenerator {

    private static final String NUMBERS = "1234567890";

    @SneakyThrows
    private static List<String> allWords() {
//        List<String> linesFile = new ArrayList<>();
//        File dictionary = Paths.get(GenerateWord.class.getResource("/words_alpha.txt").toURI()).toFile();
//        try (FileReader fileReader = new FileReader(dictionary)) {
//            linesFile = IOUtils.readLines(fileReader);
//        }
//        return linesFile;


        return lines(Paths.get(GenerateWord.class.getResource("/words_alpha.txt").toURI()).toFile().toPath())
                .collect(Collectors.toList());
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

    private String generatePassword(int length, int wordCount, char special, boolean numeric, boolean toUpperFirst) {
        StringBuilder result = new StringBuilder();
        int reserve = 0;
        if (numeric && special != 0) {
            reserve = nextInt(wordCount, length - wordCount * 3 + 1);
        }
        if (numeric && special == 0) {
            reserve = nextInt(1, length - wordCount * 3 + 1);
        }
        if (!numeric && special != 0) {
            reserve = wordCount - 1;
        }
        int newLength = length - reserve;
        while (wordCount > 1) {
            int freePlace = newLength - result.length();
            int wordLength = nextInt(3, freePlace - (wordCount - 1) * 3 + 1);
            result.append(toUpperFirst(toUpperFirst, randomWord(wordLength)));
            if (special != 0) {
                result.append(special);
                reserve--;
                newLength++;
            }
            wordCount--;
        }
        result.append(toUpperFirst(toUpperFirst, randomWord(newLength - result.length())));
        while (length > result.length()) {
            result.append(NUMBERS.charAt(nextInt(0, NUMBERS.length())));
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
 //       List<String> words = new ArrayList<>();
 //       for (String line : allWords()) {
 //           if ((line.length() == length) && (line.length() > 2)) {
 //               words.add(line);
 //           }
 //       }

        val words = allWords()
                .stream()
                .filter(s -> s.length() == length && s.length() > 2)
                .collect(Collectors.toList());
        int numberWord = nextInt(0, words.size());
        return words.get(numberWord);
    }

    public int maxLength() {
 //      int maxlength = 0;
 //      for (String line : allWords()) {
 //          if (line.length() > maxlength) {
 //              maxlength = line.length();
 //          }
 //      }

 //
 //      return maxlength;


        return allWords()
                .stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

}

