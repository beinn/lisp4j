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
package com.github.beinn.lisp4j.symbols.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.NIL;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.exceptions.ErrorException;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class LOADFunction implements ISymbol {

    private Interpreter interpreter;

    public LOADFunction(final Interpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    public List<String>  getNames() {
        return Arrays.asList("LOAD");
    }

    public SEXP call(LIST result, LIST parent) {
        InputStream inputStream;
        try {
            String name = ((ATOM)result.getExpression().get(1)).id;
            inputStream = new FileInputStream(new File(name.substring(1, name.length()-1)));
            interpreter.execute(inputStream);
        } catch (FileNotFoundException e) {
            throw new ErrorException("File not found");
        } catch (IOException e) {
            throw new ErrorException("File cannot be loaded");
        }
        
        ATOM atom = new ATOM();
        atom .id = "T";
        return atom;
    }

}
