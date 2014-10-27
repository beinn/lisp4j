package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.exceptions.ErrorException;
import com.github.lisp4j.symbols.ISymbol;

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
