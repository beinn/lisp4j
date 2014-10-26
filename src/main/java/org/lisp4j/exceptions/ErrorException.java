package org.lisp4j.exceptions;

public class ErrorException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1521418779213724993L;
    private String msg;
    public String getMessage() {
        return "Error: " + msg;
    }

    public ErrorException(final String msg) {
        this.msg = msg;
    }
    
}
