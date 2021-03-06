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
package com.github.beinn.lisp4j.symbols.functions;

import java.util.Arrays;
import java.util.List;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.symbols.ISymbol;
import com.github.beinn.lisp4j.symbols.Variable;

public class Function implements ISymbol {

    private String name;
    private LIST params;
    private LIST function;
    private Interpreter interpreter;

    public Function(final String funName, final LIST args, final LIST body, final Interpreter interpreter) {
        this.name = funName;
        this.params = args;
        this.function = body;
        this.interpreter = interpreter;
    }

    public List<String> getNames() {
        return Arrays.asList(name);
    }

    public SEXP call(final LIST result, final LIST parent) {
        final LIST newparent = new LIST();
        for (int i =0; i< params.getExpression().size();i++) {
            final ATOM symbol = (ATOM)params.getExpression().get(i);
            final SEXP value = result.getExpression().get(1 + i);
            final String sname = symbol.getId().toUpperCase();
            newparent.getLocal().put(sname, new Variable(value));
        }
        newparent.setParent(parent);
        return function.process(interpreter, true, newparent);
    }

}
