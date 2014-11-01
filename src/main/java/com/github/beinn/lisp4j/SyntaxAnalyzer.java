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

import java.util.List;
import java.util.Stack;

import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.FLAG;
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.exceptions.SyntaxErrorException;

/**
 * 
 * @author beinn
 */
public class SyntaxAnalyzer {

    private EnumState state = EnumState.START;
    private FLAG flag = FLAG.NONE;
    private final LIST root = new LIST();
    private LIST current = root;
    private final Stack<LIST> stack = new Stack<LIST>();

    private Options options;

    public SyntaxAnalyzer(final Options options) {
        if (options == null) {
            throw new IllegalArgumentException("options can not be null");
        }
        this.options = options;
        root.setNoRoot(false);
    }

    public LIST synParse(final List<Token> tokens) {
        
        for (final Token token : tokens) {
            nextToken(token);
        }
        return root;
    }

    public LIST nextToken(final Token token) {
        if (token.token.equals("(")) {
            final LIST list = new LIST();
            if (state == EnumState.NO_EVAL) {
                list.eval = false;
                list.flag = flag;
                state = EnumState.START;
            }
            current.getExpression().add(list);
            stack.push(current);
            current = list;
        } else if (token.token.equals(")")) {
            if (!stack.isEmpty()) {
                current = stack.pop();
            } else if (!options.isIgnoreTooManyParenthesis()) {
                throw new SyntaxErrorException();
            }
        } else if (token.token.equals("'")) {
            state = EnumState.NO_EVAL;
            flag = FLAG.QUOTE;
        } else if (token.token.equals("`")) {
            state = EnumState.NO_EVAL;
            flag = FLAG.QUOTE;
        } else if (token.token.equals(",")) {
            state = EnumState.NO_EVAL;
            flag = FLAG.COMMA;
        } else if (token.token.equals(",@")) {
            state = EnumState.NO_EVAL;
            flag = FLAG.COMMA_AT;
        } else {
            final ATOM atom = new ATOM();
            if (state == EnumState.NO_EVAL) {
                atom.eval = false;
                atom.flag = flag;
                state = EnumState.START;
            }
            atom.id = token.token;
            current.getExpression().add(atom);
        }
        return root;
    }
}
