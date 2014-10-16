package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.lisp4j.Interpreter;


public class QuitTest extends Base {
    
    @Test
    public void simpleQuit() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(quit)");
        assertEquals(a("Bye!"), result);
    }

    @Test
    public void quitAndIgnore() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(quit) (+ 2 3)");
        assertEquals(a("Bye!"), result);
    }
}
