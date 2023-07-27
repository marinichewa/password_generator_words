package org.example.generate;

import org.example.generate.GenerateRules;

import java.io.FileNotFoundException;

public interface PasswordGenerator {
    String generate (GenerateRules rules) throws FileNotFoundException;
}
