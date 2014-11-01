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

/**
 * 
 * @author beinn
 */
public class LexicalParser {
    private EnumState state = EnumState.START;

    final private List<Token> tokens = new ArrayList<Token>();
    final private StringBuilder buffer = new StringBuilder();

    public LexicalParser() {
        state = EnumState.START;
    }

    public List<Token> lexParse(final String code) {
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            nextChar(c);
        }
        return tokens;
    }

    public void nextChar(final char c) {
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
            } else if (c == '\'') {
                createToken(tokens, buffer);
                tokens.add(new Token("'"));
            } else if (c == ' ') {
                createToken(tokens, buffer);
            } else if (c == '"') {
                buffer.append(c);
                state = EnumState.STRING;
            } else if (c == '|') {
            } else if (c == '#') {
            } else if (c == ',') {
                createToken(tokens, buffer);
                buffer.append(c);
                createToken(tokens, buffer);
            } else if (c == '@') {
                if (tokens.size() > 0 && buffer.length() == 0 && tokens.get(tokens.size() - 1).token.equals(",")) {
                    tokens.get(tokens.size() - 1).token = ",@";
                }
            } else if (c == '\n') {
            } else if (c == '\r') {
            } else if (c == '\t') {
            } else {
                buffer.append(c);
            }
        }

    }

    private void createToken(final List<Token> tokens, final StringBuilder buffer) {
        state = EnumState.START;
        if (buffer.length() > 0) {
            tokens.add(new Token(buffer.toString()));
            buffer.setLength(0);
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }

}
