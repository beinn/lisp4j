package com.github.lisp4j.exceptions;

public class WrongArgumentTypeException extends LispException {

    /**
     * 
     */
    private static final long serialVersionUID = 1521418779213724993L;
    private String function;
    public String getMessage() {
        return function;
    }

    public WrongArgumentTypeException(final String function) {
        this.function = function;
    }
    
}
