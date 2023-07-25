package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GenerateRules {
    private int length;
    private int wordcount;
    private char special;
    private boolean numeric;
    private boolean toUpperFirst;


}
