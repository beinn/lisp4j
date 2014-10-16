package org.lisp4j.exceptions;

public class UndefinedFunctionException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1521418779213724993L;
    private String function;
    public String getMessage() {
        return "Function " + function + " not defined";
    }

    public UndefinedFunctionException(final String function) {
        this.function = function;
    }
    
}
