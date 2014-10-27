package org.jlisp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.lisp4j.Interpreter;

public class PackagesTest extends Base {

    @Test
    @Ignore
    public void inPackage() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(in-package `myapp)");
        assertEquals(a("#<\"myapp\" package>"), result);
    }

 
}
