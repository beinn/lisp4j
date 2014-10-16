package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class CARTest {
    
    @Test
    public void first() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(first '(4 2 3))");
        assertEquals("4", result);
    }
   
    @Test
    public void car() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(car '(4 2 3))");
        assertEquals("4", result);
    }
}
