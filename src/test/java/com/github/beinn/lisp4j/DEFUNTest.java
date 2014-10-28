package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;

public class DEFUNTest extends Base {
    
    @Test
    public void defun() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(defun SUM () (+ 2 3))");
        assertEquals(a("SUM"), result);
    }
    
    @Test
    public void defun_and_exec() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(defun SUM () (+ 2 3))");
        assertEquals(a("SUM"), result);
        result = lisp.execute("(SUM  2 3)");
        assertEquals(a("5.0"), result);
    }
}
