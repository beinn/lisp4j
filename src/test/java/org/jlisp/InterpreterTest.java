package org.jlisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class InterpreterTest {

    @Test
    public void simpleSum() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(+ 2 3)");
        assertEquals("5.0", result);
    }
    
    @Test
    public void simpleSum_1() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(+ 2.0 3.0 )");
        assertEquals("5.0", result);
    }
    
    @Test
    public void simpleTimes() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(* 2 3)");
        assertEquals("6.0", result);
    }
    
    @Test
    public void nestedTimesSum() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(* 2 (+ 3 4))");
        assertEquals("14.0", result);
    }
    
    @Test
    public void nestedTimesSum_2() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(* (+ 3 4) 2)");
        assertEquals("14.0", result);
    }
    
    @Test
    public void nestedTimesSum_3() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(* (+ 3 4)2)");
        assertEquals("14.0", result);
    }
    
    @Test
    public void nestedTimesSum_4() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(*(+ 3 4) 2)");
        assertEquals("14.0", result);
    }
    
    @Test
    public void nestedTimesSum_5() {
        Interpreter lisp = new Interpreter();
        String result = lisp.execute("(*(+ 3 4)2)");
        assertEquals("14.0", result);
    }
}
