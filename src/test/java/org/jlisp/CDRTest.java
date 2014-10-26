package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.lisp4j.Interpreter;


public class CDRTest extends Base {
    
    @Test
    public void cdr() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(cdr '(4 2 3))");
        assertEquals(a("(2 3)"), result);
    }
   
    @Test
    public void cdr_nil_1() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(cdr '(1))");
        assertEquals(a("NIL"), result);
    }

    @Test
    public void cdr_nil_2() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(cdr ())");
        assertEquals(a("NIL"), result);
    }

}
