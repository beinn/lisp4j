package com.github.lisp4j.symbols.functions.utils;

import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.exceptions.WrongArgumentTypeException;

public final class Numbers {

    private Numbers() {}
    
    public static double checkNumeric(final SEXP f) {
        if (!(f instanceof ATOM)) {
            throw new WrongArgumentTypeException("Error: argument is not numeric");
        }
        ATOM atom = (ATOM) f;
        try {
            return Double.parseDouble(atom.id);
        } catch (NumberFormatException e) {
            throw new WrongArgumentTypeException("Error: " + atom.id + " is not numeric");
        }
    }
}
