package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

public class MULFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("*");
    }

    public SEXP call(LIST result) {
        double acum = 1;
        for (int i = 1; i < result.expression.size(); i++) {
            acum *= Double.parseDouble(((ATOM)result.expression.get(i)).id);
        }
        ATOM atom = new ATOM();
        atom.id = String.valueOf(acum);
        return atom;
    }

}
