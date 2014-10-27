package com.github.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.github.lisp4j.Interpreter;


public class CONSTest extends Base {
    
    @Test
    @Ignore
    public void cons() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(cons 1 2))");
        assertEquals(a("4"), result);
    }
   
}
