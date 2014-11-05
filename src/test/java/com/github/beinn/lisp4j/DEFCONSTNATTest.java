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
package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.exceptions.ConstantException;

public class DEFCONSTNATTest extends Base {

    @Test
    public void DEFCONSTANT_list() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFCONSTANT parm `(1 2))");
        assertEquals(a("'(1 2)"), result);
    }

    @Test
    public void DEFCONSTANT_atom() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFCONSTANT parm 4.0)");
        assertEquals(a("4.0"), result);
    }

    @Test
    public void DEFCONSTANT() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFCONSTANT parm (+ 2 3))");
        assertEquals(a("5.0"), result);
    }

    @Test
    public void DEFCONSTANT_and_call() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFCONSTANT parm (+ 2 3))(+ 5 parm)");
        assertEquals(a("5.0", "10.0"), result);
    }

    @Test(expected = ConstantException.class)
    public void DEFCONSTANT_value_cannot_change() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(DEFCONSTANT parm (+ 2 3))(setf parm 7)");
    }

    @Test
    public void DEFCONSTANT_two_times() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFCONSTANT parm 4.0)(DEFCONSTANT parm 7.0)");
        assertEquals(a("4.0", "7.0"), result);
    }
}
