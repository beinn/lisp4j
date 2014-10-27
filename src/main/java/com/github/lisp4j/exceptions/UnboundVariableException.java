package com.github.lisp4j.exceptions;

public class UnboundVariableException extends LispException {

    /**
     * 
     */
    private static final long serialVersionUID = 1931413519953786018L;

    private String msg;
    public String getMessage() {
        return "Error: variable " + msg + " unbound";
    }
    
    public UnboundVariableException(String id) {
        this.msg = id;
    }
}
