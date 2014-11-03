/*
 *  lisp4j - Lisp Interpreter for Java
 *  Copyright (C) 2014 Javier Romo
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.beinn.lisp4j.symbols.macros;

import java.util.Arrays;
import java.util.List;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.NIL;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.symbols.ISymbol;
import com.github.beinn.lisp4j.symbols.Variable;

/**
 * Builds a new list taking the arguments as elements.
 */
public class Setf implements ISymbol {

    private Interpreter interpreter;

    public Setf(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public List<String> getNames() {
        return Arrays.asList("SETF");
    }

    public SEXP call(final LIST result, final LIST parent) {
        final SEXP value = result.getExpression().get(2).process(interpreter, true, parent);
        final String name = ((ATOM) result.getExpression().get(1)).getId().toUpperCase();
        Variable variable = parent.recoverSymbol(interpreter, name);
        if (variable == null) {
            variable = new Variable(value);
            parent.getLocal().put(name, variable);
        } else {
            variable.setValue(value);
        }
        return value;
    }

}
