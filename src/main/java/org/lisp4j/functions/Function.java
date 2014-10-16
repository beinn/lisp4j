package org.lisp4j.functions;

import java.util.List;

public class Function  implements IFunction {

    private String name;
    private String params;
    private String function;

    public Function(String funName, String params, String function) {
        this.name = funName;
        this.params = params;
        this.function = function;
    }

    public String call(List<String> tokens) {
        return null;
    }

    public String getName() {
        return name;
    }

}
