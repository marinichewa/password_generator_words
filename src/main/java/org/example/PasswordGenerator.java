package org.example;

import java.io.FileNotFoundException;

public interface PasswordGenerator {
    public String generate (GenerateRules rules) throws FileNotFoundException;
}
