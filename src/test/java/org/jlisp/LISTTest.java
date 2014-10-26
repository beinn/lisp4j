package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.lisp4j.Interpreter;
import org.lisp4j.exceptions.UndefinedFunctionException;

public class LISTTest extends Base {

    @Test
    public void list() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(list 1 2 3))");
        assertEquals(a("(1 2 3)"), result);
    }

    @Test
    public void list_no_args() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(list)");
        assertEquals(a("NIL"), result);
    }

    @Test
    public void list_nil() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(list NIL)");
        assertEquals(a("(NIL)"), result);
    }

    @Test(expected = UndefinedFunctionException.class)
    public void list_list_nil() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(list (NIL))");
        assertEquals(a("(NIL)"), result);
    }

    @Test
    public void list_empty_list() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(list ())");
        assertEquals(a("(NIL)"), result);
    }
}
