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
import com.github.beinn.lisp4j.exceptions.WrongArgumentNumbersException;


public class NUMERIC_EQ_Test extends Base {
    
    @Test
    public void equals() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1)");
        assertEquals(a("T"), result);
    }

    @Test
    public void equals_2() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1 1 1 1)");
        assertEquals(a("T"), result);
    }
    
    @Test(expected = WrongArgumentNumbersException.class)
    public void equals_6() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(=)");
    }
    
    @Test
    public void equals_4() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1 1.0)");
        assertEquals(a("T"), result);
    }
    
    @Test
    public void equals_3() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1 1 3)");
        assertEquals(a("NIL"), result);
    }
}
