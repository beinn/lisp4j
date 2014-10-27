package com.github.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.lisp4j.Interpreter;
import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.ast.SEXP;
import com.github.lisp4j.symbols.ISymbol;

public class HaltFunction implements ISymbol {

    private Interpreter interpreter;

    public HaltFunction(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public List<String>  getNames() {
        return Arrays.asList("QUIT");
    }

    public SEXP call(LIST result) {
        interpreter.setHalted(true);
        ATOM atom = new ATOM();
        atom.id = "Bye!";
        return atom;
    }

}
