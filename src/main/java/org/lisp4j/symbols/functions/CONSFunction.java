package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

public class CONSFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("CONS");
    }

    public SEXP call(LIST result) {
        throw new UnsupportedOperationException();
    }

}
