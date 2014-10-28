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

import com.github.beinn.lisp4j.Interpreter;


public class DEFMACROTest extends Base {
    
    @Test
    public void defmacro() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(defmacro sum (x y) (+ x y))");
        assertEquals(a("SUM"), result);
    }

    @Test
    public void defmacro_and_usage() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(defmacro sum (x y) (+ x y))");
        assertEquals(a("SUM"), result);
        result = lisp.execute("(sum 1 2)");
        assertEquals(a("3.0"), result);
    }
    
    @Test
    public void defmacro_and_usage_2() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(defmacro sum (x y) (+ x y))(sum 1 2)");
        assertEquals(a("SUM","3.0"), result);
    }
}
