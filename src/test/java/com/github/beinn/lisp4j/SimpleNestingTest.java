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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;

public class SimpleNestingTest extends Base {

    @Test(expected = Exception.class)
    public void wrong_defun() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(defun (SUMA) () (+ 2 3))");
    }

    @Test
    public void nil() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("()");
        assertEquals(Arrays.asList(new String[] { "NIL" }), result);
    }

    @Test
    public void simpleSum() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 2 3)");
        assertEquals(a("5.0"), result);
    }

    @Test
    public void simpleSum_with_spaces() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("  (+ 2 3) ");
        assertEquals(a("5.0"), result);
    }
    
    @Test
    public void sum_negativeNumber() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ -10.0 5)");
        assertEquals(a("-5.0"), result);
    }

    @Test
    public void circle() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(* 2 pi 1)");
        assertEquals(a("6.2831852"), result);
    }
    
    @Test
    public void sum_positiveNumber() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ +23 +03)");
        assertEquals(a("26.0"), result);
    }

    @Test
    public void simpleSum_1() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 2.0 3.0 )");
        assertEquals(a("5.0"), result);
    }

    @Test
    public void simpleTimes() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(* 2 3)");
        assertEquals(a("6.0"), result);
    }

    @Test
    public void nestedTimesSum() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(* 2 (+ 3 4))");
        assertEquals(a("14.0"), result);
    }

    @Test
    public void nestedTimesSum_2() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(* (+ 3 4) 2)");
        assertEquals(a("14.0"), result);
    }

    @Test
    public void nestedTimesSum_3() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(* (+ 3 4)2)");
        assertEquals(a("14.0"), result);
    }

    @Test
    public void nestedTimesSum_4() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(*(+ 3 4) 2)");
        assertEquals(a("14.0"), result);
    }

    @Test
    public void nestedTimesSum_5() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(*(+ 3 4)2)");
        assertEquals(a("14.0"), result);
    }

    @Test
    public void nestedTimesSum_6() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+(+ 1 2)(+ 3 4))");
        assertEquals(a("10.0"), result);
    }

    @Test
    public void nestedTimesSum_7() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+(+ 1 2)5(+ 3 4) 2 )");
        assertEquals(a("17.0"), result);
    }

    @Test
    public void nestedTimesSum_8() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 1(+ 1(+ 1(+ 1))))");
        assertEquals(a("4.0"), result);
    }
}