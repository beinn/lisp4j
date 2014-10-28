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

/**
 * 
 */
public class LispPackage {

    public Map<String, ISymbol> symbols = new HashMap<String, ISymbol>();
    public Map<String, ISymbol> functions = new HashMap<String, ISymbol>();
    public Map<String, ISymbol> macros = new HashMap<String, ISymbol>();

	public String packageName;
	public LispPackage(final String packageName) {
		this.packageName = packageName;
	}

    protected void addFun(final ISymbol function) {
        for(final String name:function.getNames()){
            functions.put(name, function);
        }
    }

    protected void addSymbol(final ISymbol function) {
        for(final String name:function.getNames()){
            symbols.put(name, function);
        }
    }

    protected void addMacro(final ISymbol function) {
        for(final String name:function.getNames()){
            macros.put(name, function);
        }
    }
}
