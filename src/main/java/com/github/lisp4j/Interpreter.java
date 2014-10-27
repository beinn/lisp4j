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
package com.github.lisp4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.lisp4j.ast.ATOM;
import com.github.lisp4j.ast.LIST;
import com.github.lisp4j.symbols.ISymbol;
import com.github.lisp4j.symbols.constants.PIConstant;
import com.github.lisp4j.symbols.functions.ATOMFunction;
import com.github.lisp4j.symbols.functions.CARFunction;
import com.github.lisp4j.symbols.functions.CDRFunction;
import com.github.lisp4j.symbols.functions.CLOSEFunction;
import com.github.lisp4j.symbols.functions.CONSFunction;
import com.github.lisp4j.symbols.functions.DEFPARAMETERFunction;
import com.github.lisp4j.symbols.functions.DistinctFunction;
import com.github.lisp4j.symbols.functions.EQFunction;
import com.github.lisp4j.symbols.functions.ErrorFunction;
import com.github.lisp4j.symbols.functions.HELPFunction;
import com.github.lisp4j.symbols.functions.HaltFunction;
import com.github.lisp4j.symbols.functions.LISTFunction;
import com.github.lisp4j.symbols.functions.MULFunction;
import com.github.lisp4j.symbols.functions.NOTFunction;
import com.github.lisp4j.symbols.functions.NUMERICEQFunction;
import com.github.lisp4j.symbols.functions.OPENFunction;
import com.github.lisp4j.symbols.functions.SECONDFunction;
import com.github.lisp4j.symbols.functions.SQRTFunction;
import com.github.lisp4j.symbols.functions.SUMFunction;

/**
 * 
 * @author beinn
 */
public class Interpreter {

    public Map<String, ISymbol> functions = new HashMap<String, ISymbol>();
    private boolean halted = false;

    public String currentPackage = "common-lisp-user";
    public List<String> uses = new ArrayList<String>();
    
    public Interpreter() {
        loadFunctions();
    }

    private void loadFunctions() {
        addFun(new NOTFunction());
        addFun(new DistinctFunction());
        addFun(new MULFunction());
        addFun(new SUMFunction());
        addFun(new ErrorFunction());
        addFun(new CARFunction());
        addFun(new CLOSEFunction());
        addFun(new DEFPARAMETERFunction(this));
        addSymbol(new PIConstant());
        addFun(new CDRFunction());
        addFun(new CONSFunction());
        addFun(new OPENFunction());
        addFun(new SECONDFunction());
        addFun(new ATOMFunction());
        addFun(new SQRTFunction());
        addFun(new EQFunction());
        addFun(new NUMERICEQFunction());
        addFun(new LISTFunction());
        addFun(new HELPFunction(this));
        addFun(new HaltFunction(this));
    }

    private void addFun(final ISymbol function) {
        for(final String name:function.getNames()){
            functions.put(name, function);
        }
    }
    private void addSymbol(final ISymbol function) {
        for(final String name:function.getNames()){
            symbols.put(name, function);
        }
    }
    private EnumState state = EnumState.START;
    public Map<String, ISymbol> symbols = new HashMap<String, ISymbol>();

    /**
     *
     * @param code
     * @return
     */
    public List<String> execute(final String code) {

        final StringBuilder buffer = new StringBuilder();
        final LIST root = new LIST();
        root.noRoot = false;
        LIST list = root;
        LIST aux = root;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (state == EnumState.COMMENT) {
                if (c == '\n') {
                    state = EnumState.START;
                }
            } else {
                
                if (c == '(') {
                    newAtom(buffer, aux);
                    list = aux;
                    aux = new LIST();
                    if (state == EnumState.NO_EVAL) {
                        aux.eval = false;
                    }
                    state = EnumState.START;
                    list.expression.add(aux);
                } else if (c == ')') {
                    newAtom(buffer, aux);
                    aux = list;
                } else if (c == ';') {
                    state = EnumState.COMMENT;
                } else if (c == '`' || c == '\'') {
                    newAtom(buffer, aux);
                    state = EnumState.NO_EVAL;
                } else if (c == ' ') {
                    newAtom(buffer, aux); 
                } else if (c == '"') {
                    newAtom(buffer, aux); // TODO
                } else if (c == '|') {
                    newAtom(buffer, aux); // TODO
                } else if (c == ',') {
                    newAtom(buffer, aux); // TODO
                } else if (c == '@') {
                    newAtom(buffer, aux); // TODO
                } else {
                    buffer.append(c);
                }
            }
        }

        return root.process(this, true).display();
    }

    private void newAtom(final StringBuilder buffer, final LIST s_expression) {

        if (buffer.length() > 0) {
            ATOM atom = new ATOM();
            if (state == EnumState.NO_EVAL) {
                atom.eval = false;
            }
            atom.id = buffer.toString();
            s_expression.expression.add(atom);
            buffer.setLength(0);
            state = EnumState.START;
        }
        
    }

    public boolean isHalted() {
        return halted;
    }

    public void setHalted(boolean b) {
        halted = b;
    }
}
