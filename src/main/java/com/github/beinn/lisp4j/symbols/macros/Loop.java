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
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.exceptions.ReturnException;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class Loop implements ISymbol {

    private Interpreter interpreter;

    public Loop(final Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public List<String> getNames() {
        return Arrays.asList("LOOP");
    }

    public SEXP call(final LIST result, final LIST parent) {
        try {
            while (true) {
                for (int i = 1; i < result.getExpression().size(); i++) {
                    result.getExpression().get(i).process(interpreter, true, parent);
                }
            }
        } catch (ReturnException e) {
            return e.getResult();
        }

    }

}
