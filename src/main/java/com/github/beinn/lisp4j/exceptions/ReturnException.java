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
package com.github.beinn.lisp4j.exceptions;

import com.github.beinn.lisp4j.ast.SEXP;

/**
 * This is a special exception which is threw by the RETURN function placed
 * inside of a LOOP.
 */
public class ReturnException extends LispException {

    private final SEXP result;

    public ReturnException(final SEXP sexp) {
        this.result = sexp;
    }

    public SEXP getResult() {
        return result;
    }


    /**
     * 
     */
    private static final long serialVersionUID = 3840298093679441217L;

}
