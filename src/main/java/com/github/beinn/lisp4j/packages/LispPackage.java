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
package com.github.beinn.lisp4j.packages;

import java.util.HashMap;
import java.util.Map;

import com.github.beinn.lisp4j.symbols.ISymbol;
import com.github.beinn.lisp4j.symbols.Variable;

/**
 * 
 */
public class LispPackage {

    private Map<String, Variable> symbols = new HashMap<String, Variable>();
    private Map<String, ISymbol> functions = new HashMap<String, ISymbol>();
    private Map<String, ISymbol> macros = new HashMap<String, ISymbol>();

	private String packageName;

	public LispPackage(final String packageName) {
		this.packageName = packageName;
	}

	public void addFun(final ISymbol function) {
        for(final String name:function.getNames()){
            getFunctions().put(name, function);
        }
    }

	public void addSymbol(final Variable function) {
        for(final String name:function.getNames()){
            getSymbols().put(name, function);
        }
    }

	public void addMacro(final ISymbol function) {
        for(final String name:function.getNames()){
            getMacros().put(name, function);
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public Map<String, Variable> getSymbols() {
        return symbols;
    }

    public void setSymbols(final Map<String, Variable> symbols) {
        this.symbols = symbols;
    }

    public Map<String, ISymbol> getFunctions() {
        return functions;
    }

    public void setFunctions(final Map<String, ISymbol> functions) {
        this.functions = functions;
    }

    public Map<String, ISymbol> getMacros() {
        return macros;
    }

    public void setMacros(final Map<String, ISymbol> macros) {
        this.macros = macros;
    }
}
