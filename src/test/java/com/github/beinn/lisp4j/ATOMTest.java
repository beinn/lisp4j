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


public class ATOMTest extends Base {
    
    @Test
    public void atom_symbol() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(atom `bla)");
        assertEquals(a("T"), result);
    }
   
    
    @Test
    public void atom_atom_sexp() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(atom (+ 2 3))");
        assertEquals(a("T"), result);
    }
    
    @Test(expected = WrongArgumentNumbersException.class)
    public void atom_wrong_args() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(atom 1 2 3)");
    }
    
    
    
    

    @Test
    public void atom_list() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(atom `(1))");
        assertEquals(a("NIL"), result);
    }
}
