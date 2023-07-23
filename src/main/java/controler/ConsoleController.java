package controler;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command (name="user", mixinStandardHelpOptions = true)
public class ConsoleController {
    @Option(names = "--length", description = "Length password", required = true)
    private int minLength;
    
}
