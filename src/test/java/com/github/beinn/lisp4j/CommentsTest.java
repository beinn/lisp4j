package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;

public class CommentsTest extends Base {

    @Test
    public void codeAndComment() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 2 3); This is a comment");
        assertEquals(a("5.0"), result);
    }

    @Test
    public void codeAndCommentAndCode() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(+ 2 3); This is a comment\n (+ 3 3);");
        assertEquals(a("5.0","6.0"), result);
    }
    
    @Test
    public void comment() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("; This is a comment");
        assertEquals(Arrays.asList(new String[0]), result);
    }

}
