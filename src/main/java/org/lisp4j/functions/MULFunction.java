package org.lisp4j.functions;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;

public class MULFunction implements IFunction {

    public String getName() {
        return "*";
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
