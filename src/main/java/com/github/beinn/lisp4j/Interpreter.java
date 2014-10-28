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
package com.github.beinn.lisp4j;

import java.util.ArrayList;
import java.util.List;

import com.github.beinn.lisp4j.packages.CommonLispPackage;
import com.github.beinn.lisp4j.packages.LispPackage;
import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.LIST;

/**
 * 
 * @author beinn
 */
public class Interpreter {

    private EnumState state = EnumState.START;

    private boolean halted = false;

    public LispPackage currentPackage;
    public List<LispPackage> uses = new ArrayList<LispPackage>();
    public List<LispPackage> packages = new ArrayList<LispPackage>();

    public Interpreter() {
    	currentPackage = new LispPackage("common-lisp-user");

    	uses.add(new CommonLispPackage(this));
    	uses.add(currentPackage);

    	packages.addAll(uses);
    }

    /**
     *
     * @param code
     * @return
     */
    public List<String> execute(final String code) {

    	// lexical parsing
    	final List<Token> tokens = lexParse(code);
    	
    	// syntactical parsing
    	LIST ast = synParse(tokens);
    	
        ast = astGen(code);

        return ast.process(this, true).display();
    }

    @Deprecated
	private LIST astGen(final String code) {
    	state = EnumState.START;
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
		return root;
	}

	private List<Token> lexParse(final String code) {
		final List<Token> tokens = new ArrayList<Token>();
		final StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			if (state == EnumState.STRING) {
				buffer.append(c);
                if (c == '"') {
                    createToken(tokens, buffer);
                }
            } else if (state == EnumState.COMMENT) {
                if (c == '\n') {
                    state = EnumState.START;
                }
            } else {
                if (c == '(') {
                	createToken(tokens, buffer);
                	tokens.add(new Token("("));
                } else if (c == ')') {
                	createToken(tokens, buffer);
                	tokens.add(new Token(")"));
                } else if (c == ';') {
                	createToken(tokens, buffer);
                	state = EnumState.COMMENT;
                } else if (c == '`') {
                	createToken(tokens, buffer);
                	tokens.add(new Token("`"));
            	} else if( c == '\'') {
            		createToken(tokens, buffer);
                	tokens.add(new Token("'"));
                } else if (c == ' ') {
                	createToken(tokens, buffer);
                } else if (c == '"') {
                	state = EnumState.STRING;
                } else if (c == '|') {
                } else if (c == '#') {
                } else if (c == ',') {
                } else if (c == '@') {
                } else {
                    buffer.append(c);
                }
            }
		}
		return tokens;
	}

	private void createToken(final List<Token> tokens,
			final StringBuilder buffer) {
		state = EnumState.START;
		if (buffer.length() > 0) {
			tokens.add(new Token(buffer.toString()));
			buffer.setLength(0);
		}
	}
    
    private LIST synParse(final List<Token> tokens) {
		final LIST root = new LIST();
		for (final Token token:tokens) {
			
		}
		return root;
	}

    @Deprecated
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
