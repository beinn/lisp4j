package com.github.lisp4j.symbols;

import java.util.List;

import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;

public interface ISymbol {

    SEXP call(LIST result);

    List<String> getNames();

}
