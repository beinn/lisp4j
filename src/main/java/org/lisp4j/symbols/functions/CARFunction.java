package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

public class CARFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("CAR", "FIRST");
    }

    public SEXP call(LIST result) {
        SEXP sexp;
        if (result.expression.size() > 1 && result.expression.get(1) instanceof LIST && !((LIST)result.expression.get(1)).expression.isEmpty()) {
            sexp = ((LIST)result.expression.get(1)).expression.get(0);
        } else {
            sexp = new NIL();
        }
        
        return sexp;
    }

}
