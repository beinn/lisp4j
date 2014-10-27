package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;
import com.github.lisp4j.symbols.functions.utils.Numbers;

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
