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

import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.NIL;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.symbols.ISymbol;

/**
 * Builds a new list taking the arguments as elements.
 */
public class LISTFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("LIST");
    }

    public SEXP call(LIST result) {
        final LIST sexp = new LIST();
        if (result.expression.size() == 1) {
            return new NIL();
        }
        for (int i = 1; i <result.expression.size();i++) {
            sexp.expression.add(result.expression.get(i));
        }
        
        return sexp;
    }

}
