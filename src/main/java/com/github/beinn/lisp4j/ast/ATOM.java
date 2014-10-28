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
package com.github.beinn.lisp4j.ast;

import java.util.Arrays;
import java.util.List;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.packages.LispPackage;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class ATOM extends SEXP {

    public String id;

    @Override
    public SEXP process(final Interpreter interpreter, final boolean b) {
        final ATOM atom = new ATOM();
        String value = null;

        try {
            Double.parseDouble(id);
            value = id;
        } catch (NumberFormatException nfe) {
            final ISymbol symbol = recoverSymbol(interpreter,id.toUpperCase());

            if (symbol != null) {
                value = ((ATOM)symbol.call(null)).id;
            } else if ("NIL".equalsIgnoreCase(id)) {
                value = "NIL";
            } else {
                value = id;
            }
        }

        atom.id = value;
        return atom;
    }

    private ISymbol recoverSymbol(final Interpreter interpreter, final String symbol) {
    	for(final LispPackage p:interpreter.packages) {
    		final ISymbol s = p.symbols.get(symbol);
    		if (s != null) {
    			return s;
    		}
    	}
		return null;
	}

	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ATOM)) {
            return false;
        }
        final ATOM atom = (ATOM) obj;
        return this.hashCode() == atom.hashCode();
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return id;
    }

    @Override
    public List<String> display() {
        return Arrays.asList(this.toString());
    }
}
