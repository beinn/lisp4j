package com.github.beinn.lisp4j.exceptions;

public class SyntaxErrorException extends LispException {

    /**
     * 
     */
    private static final long serialVersionUID = 8791504311168308045L;

    @Override
    public String getMessage() {
        return "ERROR: too many close parenthesis";
    }
}
