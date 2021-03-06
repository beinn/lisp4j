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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

public class StreamIntegrationTest extends Base {

    @Test
    public void readFromInputStream() throws IOException {
        Interpreter lisp = new Interpreter();
        InputStream inputStream = new ByteArrayInputStream("(+ 2 3)".getBytes(Charset.defaultCharset()));
        List<String> result = lisp.execute(inputStream);
        inputStream.close();
        assertEquals(a("5.0"), result);
    }
    
    @Test
    public void readFromInputFile_utf8() throws IOException {
        Interpreter lisp = new Interpreter();
        InputStream inputStream = new FileInputStream(new File("src/test/resources/test1utf8.lisp"));
        List<String> result = lisp.execute(inputStream);
        inputStream.close();
        assertEquals(a("5.0","5.0","6.0", "12.0", "6.0", "12.0"), result);
    }
    
    @Test
    public void readFromInputFile_cp1252() throws IOException {
        Interpreter lisp = new Interpreter();
        InputStream inputStream = new FileInputStream(new File("src/test/resources/test1cp1252.lisp"));
        List<String> result = lisp.execute(inputStream);
        inputStream.close();
        assertEquals(a("5.0","5.0","6.0", "12.0", "6.0", "12.0"), result);
    }
}
