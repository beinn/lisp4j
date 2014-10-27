package com.github.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.lisp4j.Interpreter;
import com.github.lisp4j.exceptions.WrongArgumentNumbersException;
import com.github.lisp4j.exceptions.WrongArgumentTypeException;

public class SQRTTest extends Base {

    @Test
    public void sqrt_1() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(sqrt 1)");
        assertEquals(a("1.0"), result);
    }

    @Test
    public void sqrt_100() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(sqrt 100)");
        assertEquals(a("10.0"), result);
    }
    
    @Test(expected = WrongArgumentNumbersException.class)
    public void sqrt_wrong() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(sqrt 100 100)");
    }

    @Test(expected = WrongArgumentNumbersException.class)
    public void sqrt_wrong2() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(sqrt)");
    }
    
    @Test(expected = WrongArgumentTypeException.class)
    public void sqrt_wrong3() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(sqrt asd)");
    }
}
