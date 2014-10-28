package com.github.beinn.lisp4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.exceptions.WrongArgumentNumbersException;


public class ATOMTest extends Base {
    
    @Test
    public void atom_symbol() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(atom `bla))");
        assertEquals(a("T"), result);
    }
   
    
    @Test
    public void atom_atom_sexp() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(atom (+ 2 3)))");
        assertEquals(a("T"), result);
    }
    
    @Test(expected = WrongArgumentNumbersException.class)
    public void atom_wrong_args() {
        Interpreter lisp = new Interpreter();
        lisp.execute("(atom 1 2 3)");
    }
    
    
    
    

    @Test
    public void atom_list() {
        Interpreter lisp = new Interpreter();
        List<String> result = lisp.execute("(atom `(1))");
        assertEquals(a("NIL"), result);
    }
}
