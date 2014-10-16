package org.jlisp;

import java.util.List;

public class MULFunction implements IFunction {

    public String call(final List<String> tokens) {
        double acum = 1;
        for (int i = 1; i < tokens.size(); i++) {
            acum *= Double.parseDouble(tokens.get(i));
        }
        return String.valueOf(acum);
    }

    public String getName() {
        return "*";
    }

}
