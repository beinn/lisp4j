package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.Interpreter;
import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

public class HELPFunction implements ISymbol {

    private Interpreter interpreter;

    public HELPFunction(final Interpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    public List<String>  getNames() {
        return Arrays.asList("HELP");
    }

    public SEXP call(LIST result) {
        final ATOM atom = new ATOM();
        final StringBuilder builder = new StringBuilder();
        for (ISymbol f:interpreter.functions.values()) {
            builder.append(f.getNames()).append("\n");
        }
        atom.id = builder.toString();
        return atom;
    }

}
