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
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.symbols.ISymbol;
import com.github.beinn.lisp4j.symbols.Variable;

public class Let implements ISymbol {

    private Interpreter interpreter;

    public Let(final Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public SEXP call(final LIST result, final LIST parent) {
    	LIST newparent = new LIST();
    	final List<SEXP> vars = ((LIST)result.getExpression().get(1)).getExpression();
    	final SEXP body = result.getExpression().get(2);
        for (SEXP s:vars) {
        	List<SEXP> statements = ((LIST)s).getExpression();
        	final String name = ((ATOM) statements.get(0)).getId().toUpperCase();
        	final ATOM value = (ATOM) statements.get(1).process(interpreter, true, parent);
        	newparent.getLocal().put(name, new Variable(value));
        }
        newparent.setParent(parent);
        return body.process(interpreter, true, newparent);
    }

    public List<String> getNames() {
        return Arrays.asList("LET");
    }

}
