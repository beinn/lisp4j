package com.github.lisp4j.symbols.functions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.NIL;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class OPENFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("OPEN");
    }

    public SEXP call(LIST result) {
        ATOM atom = new NIL();
        if(result.expression.size() > 1) {
            if (result.expression.get(1) instanceof ATOM) {
                String path = ((ATOM) result.expression.get(1)).id;
                File file = new File(path);
                atom = new ATOM();
                atom.id = file.toString();
            }
        }

        return atom;
    }

}
