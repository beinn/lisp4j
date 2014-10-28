/*
 *  lisp4j - Lisp Interpreter for Java
 *  Copyright (C) 2014 Javier Romo
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.beinn.lisp4j.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.exceptions.UndefinedFunctionException;
import com.github.beinn.lisp4j.packages.LispPackage;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class LIST extends SEXP {
    public List<SEXP> expression = new ArrayList<SEXP>();

    public boolean noRoot = true;

    @Override
    public SEXP process(final Interpreter interpreter, final boolean local_eval) {
        final boolean doit = eval && local_eval;
        final LIST result = new LIST();
        result.eval = eval;
        //check if we are in a macro
        if (!expression.isEmpty()){
        	SEXP sexp = expression.get(0);
	        ISymbol macro = null;
	    	if (sexp instanceof ATOM) {
	    		macro = recoverMacro(interpreter, ((ATOM)sexp).id.toUpperCase());
	    	}
	
	    	if (macro != null && doit) {
	    		// expand the macro
	    		return macro.call(this).process(interpreter, true);
	    	}
        }
        
        for (final SEXP sexp : expression) {
            result.expression.add(sexp.process(interpreter, doit));
            if (interpreter.isHalted()) {
                break;
            }
        }
        SEXP results = null;
        if (doit && !result.expression.isEmpty() && noRoot) {
            final String fname = result.expression.get(0).toString().toUpperCase();
            final ISymbol f = recoverFunction(interpreter, fname);
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

    private ISymbol recoverFunction(final Interpreter interpreter, final String fname) {
    	for(LispPackage p:interpreter.packages) {
    		ISymbol f = p.functions.get(fname);
        		if (f != null) {
        			return f;
        		}
    	}
		return null;
	}

    private ISymbol recoverMacro(final Interpreter interpreter, final String fname) {
    	for(LispPackage p:interpreter.packages) {
    		ISymbol f = p.macros.get(fname);
        		if (f != null) {
        			return f;
        		}
    	}
		return null;
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
