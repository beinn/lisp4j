package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.lisp4j.Interpreter;


public class DEFPARAMETERTest extends Base {
    
    @Test
    public void DEFPARAMETER() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFPARAMETER parm (+ 2 3))");
        assertEquals(a("5.0"), result);
    }
    
    @Test
    public void DEFPARAMETER_and_call() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(DEFPARAMETER parm (+ 2 3))(+ 5 parm)");
        assertEquals(a("5.0","10.0"), result);
    }
}
