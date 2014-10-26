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
    
    @Test
    public void car_list() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(car '((+ 4 2) 3))");
        assertEquals(a("(+ 4 2)"), result);
    }
    
    @Test
    public void car_nil() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(car ())");
        assertEquals(a("NIL"), result);
    }
    
    @Test
    public void car_nil_2() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(car `())");
        assertEquals(a("NIL"), result);
    }
    
    @Test
    public void car_integrated() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 1 ( car `(2)))");
        assertEquals(a("3.0"), result);
    }
}
