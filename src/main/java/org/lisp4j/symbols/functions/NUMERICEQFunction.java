package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.exceptions.WrongArgumentNumbersException;
import org.lisp4j.exceptions.WrongArgumentTypeException;
import org.lisp4j.symbols.ISymbol;
import org.lisp4j.symbols.functions.utils.Numbers;

public class NUMERICEQFunction implements ISymbol {

    public List<String> getNames() {
        return Arrays.asList("=");
    }

    public SEXP call(final LIST result) {
        final ATOM sexp;
        if (result.expression.size() < 2) {
            throw new WrongArgumentNumbersException("Errot: = requires more than zero argument");
        }
        boolean flag = true;
        final Iterator<SEXP> it = result.expression.iterator();
        it.next();
        final double f = Numbers.checkNumeric(it.next());

        while (flag && it.hasNext()) {
            final SEXP next = it.next();
            double value = Numbers.checkNumeric(next);
            flag = value == f;
        }
        if (flag) {
            sexp = new ATOM();
            sexp.id = "T";
        } else {
            sexp = new NIL();
        }

        return sexp;
    }

    

}
