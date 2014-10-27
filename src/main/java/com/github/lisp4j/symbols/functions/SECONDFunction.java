package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.NIL;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class SECONDFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("SECOND");
    }

    public SEXP call(LIST result) {
        SEXP sexp;
        if (result.expression.size() > 2 && result.expression.get(2) instanceof LIST && !((LIST)result.expression.get(2)).expression.isEmpty()) {
            sexp = ((LIST)result.expression.get(2)).expression.get(0);
        } else {
            sexp = new NIL();
        }
        
        return sexp;
    }

}
