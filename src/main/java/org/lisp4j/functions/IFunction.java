package org.lisp4j.functions;

import org.lisp4j.ast.LIST;
import org.lisp4j.ast.SEXP;

public interface IFunction {

    SEXP call(LIST result);

    String getName();

}
