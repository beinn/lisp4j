package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

public class LISTFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("LIST");
    }

    public SEXP call(LIST result) {
        final LIST sexp = new LIST();
        if (result.expression.size() == 1) {
            return new NIL();
        }
        for (int i = 1; i <result.expression.size();i++) {
            sexp.expression.add(result.expression.get(i));
        }
        
        return sexp;
    }

}
