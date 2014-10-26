package org.lisp4j.symbols;

import java.util.List;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;

public interface ISymbol {

    SEXP call(LIST result);

    List<String> getNames();

}
