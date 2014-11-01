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

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.ast.ATOM;
import com.github.beinn.lisp4j.ast.LIST;
import com.github.beinn.lisp4j.ast.SEXP;
import com.github.beinn.lisp4j.exceptions.ErrorException;
import com.github.beinn.lisp4j.exceptions.WrongArgumentNumbersException;
import com.github.beinn.lisp4j.exceptions.WrongArgumentTypeException;
import com.github.beinn.lisp4j.symbols.ISymbol;

public class AddNewFunctionTest extends Base {

    @Test
    public void atom_symbol() {
        final Interpreter lisp = new Interpreter();
        lisp.getCurrentPackage().addFun(new ISymbol() {

            public List<String> getNames() {
                return Arrays.asList("FOO");
            }

            public SEXP call(final LIST args, final LIST parent) {
                // args contain a list of symbols. The first is the
                // function/macro name
                // and the next ones are the function parameters

                // This function only requires one and only one parameter.
                // So args should contain 2 and only 2 elementse
                if (args.getExpression().size() != 2) {
                    // if there are less or more than 2 elements, a exception
                    // should be threw.
                    throw new WrongArgumentNumbersException(1);
                }

                // The expected argument should be an atom
                SEXP param = args.getExpression().get(1);
                if (!(param instanceof ATOM)) {
                    throw new WrongArgumentTypeException("argument should be and atom");
                }
                // We extract the value of the Lisp atom
                String value = ((ATOM) param).id;

                // EXECUTE the java code (example of calculate a md5 checksum)
                MessageDigest digest;
                try {
                    digest = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                    throw new ErrorException(e.getMessage());
                }

                byte[] md5 = digest.digest(value.getBytes(Charset.defaultCharset()));

                // Store the result in a Lisp atom
                ATOM atom = new ATOM();
                atom.id = String.valueOf(new BigInteger(1, md5).toString(16));

                // return the result
                return atom;
            }
        });
        List<String> result = lisp.execute("(foo 1)");
        assertEquals(a("c4ca4238a0b923820dcc509a6f75849b"), result);
    }

}
