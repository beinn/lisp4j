package org.lisp4j.ast;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.Interpreter;
import org.lisp4j.symbols.ISymbol;

public class ATOM extends SEXP {

    public String id;

    @Override
    public SEXP process(final Interpreter interpreter, final boolean b) {
        final ATOM atom = new ATOM();
        String value = null;

        try {
            Double.parseDouble(id);
            value = id;
        } catch (NumberFormatException nfe) {
            final ISymbol symbol = interpreter.symbols.get(id);
            if (symbol != null) {
                value = ((ATOM)symbol.call(null)).id;
            } else {
                value = id;
            }
        }

        atom.id = value;
        return atom;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public List<String> display() {
        return Arrays.asList(this.toString());
    }
}
