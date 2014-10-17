package org.lisp4j.functions;

import org.lisp4j.Interpreter;
import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;

public class HaltFunction implements IFunction {

    private Interpreter interpreter;

    public HaltFunction(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public String getName() {
        return "QUIT";
    }

    public SEXP call(LIST result) {
        interpreter.setHalted(true);
        ATOM atom = new ATOM();
        atom.id = "Bye!";
        return atom;
    }

}
