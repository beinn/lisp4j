package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;
import org.lisp4j.symbols.functions.utils.Numbers;

public class SUMFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("+");
    }

    public SEXP call(final LIST result) {
        double acum = 0;
        for (int i = 1; i < result.expression.size(); i++) {
            acum += Numbers.checkNumeric(result.expression.get(i));
        }
        final ATOM atom = new ATOM();
        atom.id = String.valueOf(acum);
        return atom;
    }

}
