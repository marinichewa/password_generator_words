package org.example.generate;

import java.io.FileNotFoundException;

public interface PasswordGenerator {
    String generate (GenerateRules rules) throws FileNotFoundException;
}
