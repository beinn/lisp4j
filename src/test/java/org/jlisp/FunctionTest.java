package org.jlisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.lisp4j.functions.Function;


public class FunctionTest {
    
    @Test
    public void defun() {
        Function func = new Function("SUM","","");
        //String result = func.call(null);
        //assertEquals("SUMA", result);
    }
    
    
}
