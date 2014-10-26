package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;
import org.lisp4j.exceptions.ErrorException;
import org.lisp4j.symbols.ISymbol;

public class ErrorFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("ERROR");
    }

    public SEXP call(LIST result) {
        String msg = "";
        if (result.expression.size() > 1) {
            msg = result.expression.get(1).toString();
        }
        throw new ErrorException(msg);
    }

}
