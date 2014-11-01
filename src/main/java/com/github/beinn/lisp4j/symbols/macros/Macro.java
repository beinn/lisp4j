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

public class Macro implements ISymbol {

	private String name;
    private LIST params;
    private LIST body;
    private Interpreter interpreter;

	public Macro(final String funName, final LIST args, final LIST body, final Interpreter interpreter) {
        this.name = funName;
        this.params = args;
        this.body = body;
        this.interpreter = interpreter;
	}

	public SEXP call(final LIST result, final LIST parent) {
	    final LIST newparent = new LIST();
        for (int i =0; i< params.getExpression().size();i++) {
            final ATOM symbol = (ATOM)params.getExpression().get(i);
            final SEXP value = result.getExpression().get(1 + i);
            final String sname = symbol.id.toUpperCase();
            newparent.getLocal().put(sname, new ISymbol() {
                
                public List<String> getNames() {
                    return null;
                }
                
                public SEXP call(final LIST result, final LIST parent) {
                    return value;
                }
            });
        }
        newparent.setParent(parent);
        return body.process(interpreter, true, newparent);
	}

	public List<String> getNames() {
		return Arrays.asList(name);
	}

}
