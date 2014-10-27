package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class Function  implements ISymbol {

    private String name;
    private String params;
    private String function;

    public Function(String funName, String params, String function) {
        this.name = funName;
        this.params = params;
        this.function = function;
    }

    public String call(List<String> tokens) {
        return null;
    }

    public List<String>  getNames() {
        return Arrays.asList(name);
    }

    public SEXP call(LIST result) {
        // TODO Auto-generated method stub
        return null;
    }

}
