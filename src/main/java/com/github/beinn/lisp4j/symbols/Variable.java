package com.github.beinn.lisp4j.symbols;

import java.util.List;

import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.SEXP;

public class Variable implements ISymbol {

    private SEXP value;
    
    public Variable (SEXP value2) {
        this.value = value2;
    }
    public SEXP call(LIST args, LIST parent) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getNames() {
        // TODO Auto-generated method stub
        return null;
    }

    public SEXP getValue() {
        return value;
    }

    public void setValue(SEXP value) {
        this.value = value;
    }

}
