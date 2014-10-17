package org.lisp4j.ast;

import java.util.List;

import org.lisp4j.Interpreter;

public class ATOM extends SEXP {

    public String id;

    @Override
    public SEXP process(Interpreter interpreter, boolean b) {
        final ATOM atom = new ATOM();
        // TODO - resolve if is a symbol, so far is a string
        atom.id = id;
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
