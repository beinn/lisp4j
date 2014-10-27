package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.exceptions.WrongArgumentNumbersException;
import com.github.lisp4j.symbols.ISymbol;
import com.github.lisp4j.symbols.functions.utils.Numbers;

public class SQRTFunction implements ISymbol {

    public List<String> getNames() {
        return Arrays.asList("SQRT");
    }

    public SEXP call(LIST result) {

        if (result.expression.size() != 2) {
            throw new WrongArgumentNumbersException("Errot: SQRT requires one argument");
        }

        final ATOM sexp = new ATOM();
        sexp.id = String.valueOf(Math.sqrt(Numbers.checkNumeric(result.expression.get(1))));

        return sexp;
    }

}
