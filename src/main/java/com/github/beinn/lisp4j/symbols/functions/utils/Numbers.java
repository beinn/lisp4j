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
package com.github.beinn.lisp4j.symbols.functions.utils;

import com.github.beinn.lisp4j.exceptions.WrongArgumentTypeException;
import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.SEXP;

public final class Numbers {

    private Numbers() {}
    
    public static double checkNumeric(final SEXP f) {
        if (!(f instanceof ATOM)) {
            throw new WrongArgumentTypeException("Error: argument is not numeric");
        }
        ATOM atom = (ATOM) f;
        try {
            return Double.parseDouble(atom.id);
        } catch (NumberFormatException e) {
            throw new WrongArgumentTypeException("Error: " + atom.id + " is not numeric");
        }
    }
}
