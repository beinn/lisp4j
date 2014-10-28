package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;


public class InterpreterTest {
    
    @Test
    public void first() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 1 2)(+ 3 4)");
        assertEquals(Arrays.asList(new String[] {"3.0","7.0"}), result);
    }
    
    
}
