package org.lisp4j.ast;

import java.util.List;

import org.lisp4j.Interpreter;
import org.lisp4j.exceptions.ErrorException;
import org.lisp4j.symbols.ISymbol;

public class ATOM extends SEXP {

    public String id;

    @Override
    public SEXP process(Interpreter interpreter, boolean b) {
        final ATOM atom = new ATOM();
        String value = null;

        try {
            Double.parseDouble(id);
            value = id;
        } catch (NumberFormatException nfe) {
            ISymbol symbol = interpreter.symbols.get(id);
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
        // TODO Auto-generated method stub
        return null;
    }
}
