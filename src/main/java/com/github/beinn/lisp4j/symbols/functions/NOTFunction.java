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

import com.github.beinn.lisp4j.exceptions.WrongArgumentNumbersException;
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.NIL;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class NOTFunction implements ISymbol {

    public List<String>  getNames() {
        return Arrays.asList("NOT");
    }

    public SEXP call(final LIST result, LIST parent) {
        if (result.getExpression().size() != 2) {
            throw new WrongArgumentNumbersException("Errot: NOT requires only 1 argument");
        }
        final LIST lst = new LIST();
        lst.getExpression().add(null);
        lst.getExpression().add(new NIL());
        lst.getExpression().add(result.getExpression().get(1));
        return new EQFunction().call(lst, null);
    }

}
