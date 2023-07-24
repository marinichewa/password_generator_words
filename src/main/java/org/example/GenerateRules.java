package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class GenerateRules {
    private int length;
    private int wordcount;
    private char special;
    private boolean numeric;
    private boolean toUpperFirst;


}
