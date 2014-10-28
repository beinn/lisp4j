package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.exceptions.WrongArgumentNumbersException;


public class NUMERIC_EQ_Test extends Base {
    
    @Test
    public void equals() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1)");
        assertEquals(a("T"), result);
    }

    @Test
    public void equals_2() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1 1 1 1)");
        assertEquals(a("T"), result);
    }
    
    @Test(expected = WrongArgumentNumbersException.class)
    public void equals_6() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(=)");
    }
    
    @Test
    public void equals_4() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1 1.0)");
        assertEquals(a("T"), result);
    }
    
    @Test
    public void equals_3() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(= 1 1 3)");
        assertEquals(a("NIL"), result);
    }
}
