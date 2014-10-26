package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.exceptions.WrongArgumentNumbersException;
import org.lisp4j.symbols.ISymbol;

public class NOTFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("NOT");
    }

    public SEXP call(final LIST result) {
        if (result.expression.size() != 2) {
            throw new WrongArgumentNumbersException("Errot: NOT requires only 1 argument");
        }
        final LIST lst = new LIST();
        lst.expression.add(null);
        lst.expression.add(new NIL());
        lst.expression.add(result.expression.get(1));
        return new EQFunction().call(lst);
    }

}
