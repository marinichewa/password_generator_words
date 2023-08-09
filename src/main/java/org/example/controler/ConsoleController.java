package org.example.controler;
import org.example.generate.GenerateRules;
import org.example.generate.GenerateWord;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.PrintStream;
import java.util.logging.Logger;

@Command(name = "user")
public class ConsoleController implements Runnable {
    @Option(names = "--lth", description = "Length of password", required = true)
    private int length;

    @Option(names = {"-wc", "--count"}, description = "Words count")
    private int wordCount = 1;

    @Option(names = {"-s", "--spec"}, description = "To split words some symbols (use symbol except 0)")
    private char special;

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
     //   GenerateRules rules = rulesBuilder.build();
     //   GenerateWord word = new GenerateWord();
     //   String password = word.generate(rules);
        String password = new GenerateWord().generate(rulesBuilder.build());
        System.out.println(password);

    }
    PrintStream out = System.out;


    private void validate() {
        if (length < 3) {
            out.println("Minimal length must be 3");
            System.exit(1);
        }
        if (wordCount < 1) {
            out.println("Minimal count of word must be 1");
            System.exit(1);
        }
        if (!numeric && special == 0 && length / wordCount < 3) {
            out.println("Length of password is short for" + wordCount + " words");
            System.exit(1);
        }
        if (numeric && special == 0 && (length - 1) / wordCount < 3) {
            out.println("Length of password is short for " + wordCount + " words and numbers");
            System.exit(1);
        }
        if (!numeric && special != 0 && (length - wordCount + 1) / wordCount < 3) {
            out.println("Length of password is short for  " + wordCount + " words and symbols");
            System.exit(1);
        }
        if (numeric && special != 0 && (length - wordCount) / wordCount < 3) {
            out.println("Length of password is short for  " + wordCount + " words and symbols and numbers");
            System.exit(1);
        }
        if (wordCount == 1 && special != 0) {
            out.println("mast be minimum 2 words to split of symbols");
            System.exit(1);
        }
    }
}
