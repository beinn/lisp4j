package org.lisp4j.functions;

import java.util.List;

import org.lisp4j.Interpreter;

public class HaltFunction implements IFunction {

    private Interpreter interpreter;

    public HaltFunction(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    public String call(List<String> tokens) {
        interpreter.setHalted(true);
        return "Bye!";
    }

    public String getName() {
        return "QUIT";
    }

}
