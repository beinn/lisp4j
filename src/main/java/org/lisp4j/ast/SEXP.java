package org.lisp4j.ast;

import java.util.List;

import org.lisp4j.Interpreter;

public abstract class SEXP {
    public boolean eval = true;

    public abstract SEXP process(Interpreter interpreter, boolean b);

    public abstract List<String> display();
}
