package org.lisp4j.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lisp4j.Interpreter;
import org.lisp4j.exceptions.UndefinedFunctionException;
import org.lisp4j.symbols.ISymbol;

public class LIST extends SEXP {
    public List<SEXP> expression = new ArrayList<SEXP>();

    public boolean noRoot = true;

    @Override
    public SEXP process(Interpreter interpreter, boolean local_eval) {
        final boolean doit = eval && local_eval;
        final LIST result = new LIST();
        result.eval = eval;
        for (SEXP sexp : expression) {
            result.expression.add(sexp.process(interpreter, doit));
            if (interpreter.isHalted()) {
                break;
            }
        }
        SEXP results = null;
        if (doit && !result.expression.isEmpty() && noRoot) {
            final String fname = result.expression.get(0).toString().toUpperCase();
            final ISymbol f = interpreter.functions.get(fname);
            if (f != null) {
                results = f.call(result);
            } else {
                throw new UndefinedFunctionException(fname);
            }
        } else if (!noRoot) {
            result.noRoot = noRoot;
            return result;
        } else if (result.expression.isEmpty()) {
            return new NIL();
        } else if (!doit) {
            return result;
        }
        return results;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (!eval)
            builder.append("'");
        if (noRoot)
            builder.append("(");
        Iterator<SEXP> it = expression.iterator();
        while (it.hasNext()) {
            SEXP sexp = it.next();
            builder.append(sexp.toString());
            if (it.hasNext()) {
                builder.append(" ");
            }
        }
        if (noRoot)
            builder.append(")");
        return builder.toString();
    }

    @Override
    public List<String> display() {
        final List<String> list = new ArrayList<String>();
        Iterator<SEXP> it = expression.iterator();
        while (it.hasNext()) {
            SEXP sexp = it.next();
            list.add(sexp.toString());
        }
        return list;
    }
}
