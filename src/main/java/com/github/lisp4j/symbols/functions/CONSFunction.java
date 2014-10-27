package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.NIL;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class CONSFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("CONS");
    }

    public SEXP call(LIST result) {
        throw new UnsupportedOperationException();
    }

}
