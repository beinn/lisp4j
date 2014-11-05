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

import java.util.Arrays;
import java.util.List;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.exceptions.UnboundVariableException;
import com.github.beinn.lisp4j.symbols.Variable;

/**
 * 
 * @author beinn
 *
 */
public class LispObject extends SEXP {

    public String getLabel() {
        return label;
    }

    public void setLabel(final String id) {
        this.label = id;
    }

    private String label;

    @Override
    public SEXP process(final Interpreter interpreter, final boolean b, final LIST parent) {
        SEXP sexp;
        LispObject atom = new LispObject();
        if (!b || !eval) {
            atom.label = label;
            return atom;
        }
        try {
            Double.parseDouble(label);

            atom.label = label;
            sexp = atom;
        } catch (NumberFormatException nfe) {
            if (label.startsWith("\"") && label.endsWith("\"")) {
                atom.label = label;
                sexp = atom;
            } else {
                final Variable symbol = parent.recoverSymbol(interpreter, label.toUpperCase());

                if (symbol != null) {
                    sexp = symbol.getValue();
                } else if ("NIL".equalsIgnoreCase(label)) {
                    atom.label = "NIL";
                    sexp = atom;
                } else if ("T".equalsIgnoreCase(label)) {
                    atom.label = "T";
                    sexp = atom;
                } else {
/*                    atom.id = id;
                    sexp = atom;*/
                    throw new UnboundVariableException(label);
                }
            }
        }

        return sexp;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public List<String> display() {
        return Arrays.asList(this.toString());
    }
}
