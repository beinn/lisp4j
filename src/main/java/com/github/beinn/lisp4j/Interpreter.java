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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.packages.CommonLispPackage;
import com.github.beinn.lisp4j.packages.LispPackage;

/**
 * 
 * @author beinn
 */
public class Interpreter {

    private boolean halted = false;

    private LispPackage currentPackage;
    private List<LispPackage> uses = new ArrayList<LispPackage>();
    private List<LispPackage> packages = new ArrayList<LispPackage>();

    private Options options = new Options();

    /**
     * 
     * @return
     */
    public Options getOptions() {
        return options;
    }

    /**
     * 
     * @param options
     */
    public void setOptions(final Options options) {
        this.options = options;
    }

    /**
     * 
     */
    public Interpreter() {
        setCurrentPackage(new LispPackage("common-lisp-user"));

        uses.add(new CommonLispPackage(this));
        uses.add(getCurrentPackage());

        getPackages().addAll(uses);

    }

    /**
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public List<String> execute(final InputStream inputStream) throws IOException {

        final LexicalParser lexical = new LexicalParser();
        final Reader reader = new InputStreamReader(inputStream);
        int i;

        i = reader.read();
        while (i != -1) {
            char c = (char) i;
            lexical.nextChar(c);
            i = reader.read();
        }

        // syntactical parsing
        final SyntaxAnalyzer syntax = new SyntaxAnalyzer(options);
        final LIST ast = syntax.synParse(lexical.getTokens());

        return ast.process(this, true, null).display();
    }

    /**
     *
     * @param code
     * @return
     */
    public List<String> execute(final String code) {

        // lexical parsing
        final List<Token> tokens = new LexicalParser().lexParse(code);

        // syntactical parsing
        final LIST ast = new SyntaxAnalyzer(options).synParse(tokens);

        return ast.process(this, true, null).display();
    }

    /**
     * 
     * @return
     */
    public boolean isHalted() {
        return halted;
    }

    /**
     * 
     * @param b
     */
    public void setHalted(boolean b) {
        halted = b;
    }

    /**
     * 
     * @return
     */
    public LispPackage getCurrentPackage() {
        return currentPackage;
    }

    /**
     * 
     * @param currentPackage
     */
    public void setCurrentPackage(final LispPackage currentPackage) {
        this.currentPackage = currentPackage;
    }

    /**
     * 
     * @return
     */
    public List<LispPackage> getPackages() {
        return packages;
    }

    /**
     * 
     * @param packages
     */
    public void setPackages(final List<LispPackage> packages) {
        this.packages = packages;
    }

}
