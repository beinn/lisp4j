package org.lisp4j.symbols.constants;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

public class PIConstant implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("PI");
    }

    public SEXP call(final LIST result) {
        ATOM atom = new ATOM();
        atom.id = String.valueOf(3.1415926);
        return atom;
    }

}
