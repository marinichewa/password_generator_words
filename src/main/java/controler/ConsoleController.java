package controler;

import generate.GenerateRules;
import generate.GenerateWord;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.FileNotFoundException;

@Command(name = "user")
public class ConsoleController implements Runnable {
    @Option(names = "--lth", description = "Length of password", required = true)
    private int length;

    @Option(names = {"-wc", "--count"}, description = "Words count")
    private int wordCount = 1;

    @Option(names = {"-s", "--spec"}, description = "To split words some symbols (use symbol except 0)")
    private char special = 0;

    @Option(names = {"-n", "--num"}, description = "Use numbers")
    private boolean numeric;

    @Option(names = {"-u", "--upper"}, description = "Begins with a capital letter")
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
            System.out.println("Minimal length must be 3");
            System.exit(1);
        }
        if (wordCount < 1) {
            System.out.println("Minimal count of word must be 1");
            System.exit(1);
        }
        if (!numeric && special == 0 && length / wordCount < 3) {
            System.out.println("Length of password is short for" + wordCount + " words");
            System.exit(1);
        }
        if (numeric && special == 0 && (length - 1) / wordCount < 3) {
            System.out.println("Length of password is short for " + wordCount + " words and numbers");
            System.exit(1);
        }
        if (!numeric && special != 0 && (length - wordCount + 1) / wordCount < 3) {
            System.out.println("Length of password is short for  " + wordCount + " words and symbols");
            System.exit(1);
        }
        if (numeric && special != 0 && (length - wordCount) / wordCount < 3) {
            System.out.println("Length of password is short for  " + wordCount + " words and symbols and numbers");
            System.exit(1);
        }
        if (wordCount == 1 && special != 0) {
            System.out.println("mast be minimum 2 words to split of symbols");
            System.exit(1);
        }


    }
}
