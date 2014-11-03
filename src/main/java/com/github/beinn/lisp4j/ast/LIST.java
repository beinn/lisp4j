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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.exceptions.UndefinedFunctionException;
import com.github.beinn.lisp4j.packages.LispPackage;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class LIST extends SEXP {

	private List<SEXP> expression = new ArrayList<SEXP>();
	private Map<String, ISymbol> local = new HashMap<String, ISymbol>();
	private LIST parent;
	private boolean noRoot = true;

	@Override
	public SEXP process(final Interpreter interpreter,
			final boolean local_eval, final LIST parent) {
		this.setParent(parent);
		final boolean doit = (flag == FLAG.COMMA) || (eval && local_eval);
		final LIST result = new LIST();
		result.eval = eval;
		// check if we are in a macro
		if (!getExpression().isEmpty()) {
			SEXP sexp = getExpression().get(0);
			ISymbol macro = null;
			if (sexp instanceof ATOM) {
				macro = recoverMacro(interpreter, ((ATOM) sexp).getId()
						.toUpperCase());
			}

			if (macro != null && doit) {
				// expand the macro
				return macro.call(this, null).process(interpreter, true, this);
			}
		}

		for (final SEXP sexp : getExpression()) {
			result.getExpression().add(sexp.process(interpreter, doit, this));
			if (interpreter.isHalted()) {
				break;
			}
		}
		SEXP results = null;
		if (doit && !result.getExpression().isEmpty() && isNoRoot()) {
			final String fname = result.getExpression().get(0).toString()
					.toUpperCase();
			final ISymbol f = recoverFunction(interpreter, fname);
			if (f != null) {
				results = f.call(result, null);
			} else {
				throw new UndefinedFunctionException(fname);
			}
		} else if (!isNoRoot()) {
			result.setNoRoot(noRoot);
			return result;
		} else if (result.getExpression().isEmpty()) {
			return new NIL();
		} else if (!doit) {
			return result;
		}
		return results;
	}

	private ISymbol recoverFunction(final Interpreter interpreter,
			final String fname) {
		for (LispPackage p : interpreter.getPackages()) {
			ISymbol f = p.getFunctions().get(fname);
			if (f != null) {
				return f;
			}
		}
		return null;
	}

	private ISymbol recoverMacro(final Interpreter interpreter,
			final String fname) {
		for (LispPackage p : interpreter.getPackages()) {
			ISymbol f = p.getMacros().get(fname);
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
		if (isNoRoot())
			builder.append("(");
		Iterator<SEXP> it = getExpression().iterator();
		while (it.hasNext()) {
			SEXP sexp = it.next();
			builder.append(sexp.toString());
			if (it.hasNext()) {
				builder.append(" ");
			}
		}
		if (isNoRoot())
			builder.append(")");
		return builder.toString();
	}

	@Override
	public List<String> display() {
		final List<String> list = new ArrayList<String>();
		Iterator<SEXP> it = getExpression().iterator();
		while (it.hasNext()) {
			SEXP sexp = it.next();
			list.add(sexp.toString());
		}
		return list;
	}

	public Map<String, ISymbol> getLocal() {
		return local;
	}

	public void setLocal(Map<String, ISymbol> local) {
		this.local = local;
	}

	public LIST getParent() {
		return parent;
	}

	public void setParent(LIST parent) {
		this.parent = parent;
	}

	public boolean isNoRoot() {
		return noRoot;
	}

	public void setNoRoot(boolean noRoot) {
		this.noRoot = noRoot;
	}

	public List<SEXP> getExpression() {
		return expression;
	}

	public void setExpression(List<SEXP> expression) {
		this.expression = expression;
	}

	public ISymbol findLocalSymbol(final String symbol) {
		final ISymbol val = getLocal().get(symbol);
		if (val != null) {
			return val;
		}
		if (getParent() != null) {
			return getParent().findLocalSymbol(symbol);
		}
		return null;
	}

	public ISymbol recoverSymbol(final Interpreter interpreter,
			final String symbol) {
		final ISymbol val = findLocalSymbol(symbol);
		if (val != null) {
			return val;
		}
		for (final LispPackage p : interpreter.getPackages()) {
			final ISymbol s = p.getSymbols().get(symbol);
			if (s != null) {
				return s;
			}
		}
		return null;
	}
}
