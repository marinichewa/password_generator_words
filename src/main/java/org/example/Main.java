package org.example;

import org.example.controler.ConsoleController;
import picocli.CommandLine;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new CommandLine(new ConsoleController()).execute(args);
    }
}




