package com.github.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.lisp4j.Interpreter;

public class ListTest extends Base {

    @Test
    public void simpleList() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("'(1 2 3)");
        assertEquals(a("'(1 2 3)"), result);
    }

    @Test
    public void simpleListB() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("`(1 2 3)");
        assertEquals(a("'(1 2 3)"), result);
    }

}
