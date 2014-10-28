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

import org.junit.Ignore;
import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;


public class DEFPARAMETERTest extends Base {
    
    @Test
    public void DEFPARAMETER() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFPARAMETER parm (+ 2 3))");
        assertEquals(a("5.0"), result);
    }
    
    @Test
    @Ignore
    public void DEFPARAMETER_and_call() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFPARAMETER parm (+ 2 3))(+ 5 parm)");
        assertEquals(a("5.0","10.0"), result);
    }
}
