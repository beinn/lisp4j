package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;

public class PackagesTest extends Base {

    @Test
    @Ignore
    public void inPackage() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(in-package `myapp)");
        assertEquals(a("#<\"myapp\" package>"), result);
    }

 
}
