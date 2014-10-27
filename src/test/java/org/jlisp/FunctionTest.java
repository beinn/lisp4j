package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.lisp4j.Interpreter;


public class FunctionTest extends Base {
    
    @Test
    @Ignore
    public void defun() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(defun SUMA () (+ 2 3))");
        assertEquals(a("SUMA"), result);
    }
    
    
}
