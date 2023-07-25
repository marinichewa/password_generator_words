package controler;

import generate.GenerateRules;
import generate.GenerateWord;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.FileNotFoundException;

@Command(name = "user")
public class ConsoleController implements Runnable {
    @Option(names = "--lth", description = "Довжина пароля", required = true)
    private int length;

    @Option(names = {"-wc", "--count"}, description = "Задати кількість слів")
    private int wordCount = 1;

    @Option(names = {"-s", "--spec"}, description = "Розділити слова символом (вказати символ)")
    private char special = 0;

    @Option(names = {"-n", "--num"}, description = "Додати цифри")
    private boolean numeric;

    @Option(names = {"-u", "--upper"}, description = "Починати слова з великої літери")
    private boolean toUpperFirst;


    @Override
    public void run() {
        validate();
        GenerateRules.GenerateRulesBuilder rulesBuilder = GenerateRules.builder()
                .length(length)
                .wordcount(wordCount)
                .numeric(numeric)
                .toUpperFirst(toUpperFirst);
        if (special != 0) {
            rulesBuilder.special(special);
        }
        GenerateRules rules = rulesBuilder.build();
        GenerateWord word = new GenerateWord();
        try {
            String password = word.generate(rules);
            System.out.println(password);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate() {
        if (length < 3) {
            System.out.println("Довжина пароля повинна бути мінімум 3 символи");
            System.exit(1);
        }
        if (!numeric && special == 0 && length / wordCount < 3) {
            System.out.println("Довжина пароля коротка, щоб вмістити " + wordCount + " слів");
            System.exit(1);
        }
        if (numeric && special == 0 && (length - 1) / wordCount < 3) {
            System.out.println("Довжина пароля коротка, щоб вмістити " + wordCount + " слів і цифри");
            System.exit(1);
        }
        if (!numeric && special != 0 && (length - wordCount + 1) / wordCount < 3) {
            System.out.println("Довжина пароля коротка, щоб вмістити " + wordCount + " слів і символи");
            System.exit(1);
        }
        if (numeric && special != 0 && (length - wordCount) / wordCount < 3) {
            System.out.println("Довжина пароля коротка, щоб вмістити " + wordCount + " слів, символи і цифри");
            System.exit(1);
        }


    }
}
