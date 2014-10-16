package org.jlisp;

import java.util.List;

public class SUMFunction implements IFunction {

    public String call(final List<String> tokens) {
        double acum = 0;
        for (int i = 1; i < tokens.size(); i++) {
            acum += Double.parseDouble(tokens.get(i));
        }
        return String.valueOf(acum);
    }

    public String getName() {
        return "+";
    }

}
