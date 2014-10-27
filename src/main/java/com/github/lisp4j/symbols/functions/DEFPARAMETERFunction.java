package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.Interpreter;
import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class DEFPARAMETERFunction implements ISymbol {

    private Interpreter interpreter;

    public DEFPARAMETERFunction(final Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public List<String> getNames() {
        return Arrays.asList("DEFPARAMETER");
    }

    public SEXP call(LIST result) {
        final ATOM atom = new ATOM();
        
        if (result.expression.size() > 2) {
            final String name = ((ATOM) result.expression.get(1)).id.toUpperCase();
            final String value = ((ATOM) result.expression.get(2)).id;
            atom.id = value;
            interpreter.symbols.put(name, new ISymbol() {
                
                public List<String> getNames() {
                    return Arrays.asList(name);
                }
                
                public SEXP call(final LIST result) {
                    return atom;
                }
            });
        }
        return atom;
    }

}
