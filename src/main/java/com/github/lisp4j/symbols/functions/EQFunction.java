package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.NIL;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.exceptions.WrongArgumentNumbersException;
import com.github.lisp4j.symbols.ISymbol;

public class EQFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("EQ");
    }

    public SEXP call(LIST result) {
        final ATOM sexp;
        if (result.expression.size() != 3) {
            throw new WrongArgumentNumbersException("Errot: EQ requires only 2 argument");
        }
        if (result.expression.get(1).equals(result.expression.get(2))) {
            sexp = new ATOM();
            sexp.id = "T";
        } else {
            sexp = new NIL(); 
        }
        
        return sexp;
    }

}
