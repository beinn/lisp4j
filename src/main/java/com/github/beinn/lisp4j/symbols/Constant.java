package com.github.beinn.lisp4j.symbols;

import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.exceptions.ConstantException;

public class Constant extends Variable {

    public Constant(SEXP value2) {
        super(value2);
    }

    public void setValue(SEXP value) {
        throw new ConstantException();
    }
}
