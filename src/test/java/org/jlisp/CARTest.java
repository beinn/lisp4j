package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.lisp4j.Interpreter;


public class CARTest extends Base {
    
    @Test
    public void first() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(first '(4 2 3))");
        assertEquals(a("4"), result);
    }
   
    @Test
    public void car() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(car '(4 2 3))");
        assertEquals(a("4"), result);
    }
}
