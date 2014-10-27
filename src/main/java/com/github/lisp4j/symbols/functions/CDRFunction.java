package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.NIL;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class CDRFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("CDR");
    }

    public SEXP call(final LIST result) {
        SEXP sexp;
        if (result.expression.size() > 1 && result.expression.get(1) instanceof LIST && ((LIST)result.expression.get(1)).expression.size() > 1) {
            LIST lst = new LIST();
            List<SEXP> internal = ((LIST)result.expression.get(1)).expression;
            for (int i = 1; i < internal.size(); i++) {
                lst.expression.add(internal.get(i));
            }
            sexp = lst;
        } else {
            sexp = new NIL();
        }
        
        return sexp;
    }

}
