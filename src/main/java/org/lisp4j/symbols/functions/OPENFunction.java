package org.lisp4j.symbols.functions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.ast.NIL;
import org.lisp4j.ast.SEXP;
import org.lisp4j.symbols.ISymbol;

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
