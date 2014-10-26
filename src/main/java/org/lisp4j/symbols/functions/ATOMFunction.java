package org.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.exceptions.WrongArgumentNumbersException;
import org.lisp4j.symbols.ISymbol;

public class ATOMFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("ATOM");
    }

    public SEXP call(LIST result) {
        final ATOM sexp;
        if (result.expression.size() != 2) {
            throw new WrongArgumentNumbersException("Errot: ATOM requires only 1 argument");
        }
        if (result.expression.get(1) instanceof ATOM) {
            sexp = new ATOM();
            sexp.id = "T";
        } else {
            sexp = new NIL(); 
        }
        
        return sexp;
    }

}
