package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.Interpreter;
import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.NIL;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

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
