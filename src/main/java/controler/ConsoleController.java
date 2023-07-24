package controler;
import org.example.GeneratorRules;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command (name="user")
public class ConsoleController implements Runnable {
    @Option(names = "--lth", description = "Довжина пароля", required = true)
    private int length;

    @Option(names = {"-wc", "--count"}, description = "Задати кількість слів")
    private int wordcount=1;

    @Option(names = {"-s", "--spec"}, description = "Розділити слова символом (вказати символ)")
    private char special;

    @Option(names = {"-n", "--num"}, description = "Додати цифри")
    private boolean numeric;

    @Option(names = {"-u", "--upper"}, description = "Починати слова з великої літери")
    private boolean toUpperFirst;



    @Override
    public void run() {
        validate();

    }

    private void validate() {
        if (length<3) {
            System.out.println("Довжина пароля повинна бути мінімум 3 символи");
            System.exit(1);}
        if (!numeric && special==0 && length/wordcount<3) {
            System.out.println("Довжина пароля коротка, щоб вмістити" + wordcount + "слів" );
            System.exit(1);}
        if (numeric && special==0 && (length-1)/wordcount<3) {
            System.out.println("Довжина пароля коротка, щоб вмістити" + wordcount + "слів і цифри" );
            System.exit(1);}
        if (!numeric && special==0 && (length-wordcount-1)/wordcount<3) {
            System.out.println("Довжина пароля коротка, щоб вмістити" + wordcount + "слів і символи" );
            System.exit(1);}
        if (numeric && special==0 && (length-wordcount-2)/wordcount<3) {
            System.out.println("Довжина пароля коротка, щоб вмістити" + wordcount + "слів, символи і цифри" );
            System.exit(1);}


    }


}
