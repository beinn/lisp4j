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

import com.github.beinn.lisp4j.Interpreter;
import com.github.beinn.lisp4j.symbols.contacts.PIConstant;
import com.github.beinn.lisp4j.symbols.functions.ATOMFunction;
import com.github.beinn.lisp4j.symbols.functions.CARFunction;
import com.github.beinn.lisp4j.symbols.functions.CDRFunction;
import com.github.beinn.lisp4j.symbols.functions.CLOSEFunction;
import com.github.beinn.lisp4j.symbols.functions.CONSFunction;
import com.github.beinn.lisp4j.symbols.functions.DEFPARAMETERFunction;
import com.github.beinn.lisp4j.symbols.functions.DistinctFunction;
import com.github.beinn.lisp4j.symbols.functions.EQFunction;
import com.github.beinn.lisp4j.symbols.functions.ErrorFunction;
import com.github.beinn.lisp4j.symbols.functions.HELPFunction;
import com.github.beinn.lisp4j.symbols.functions.HaltFunction;
import com.github.beinn.lisp4j.symbols.functions.LISTFunction;
import com.github.beinn.lisp4j.symbols.functions.LOADFunction;
import com.github.beinn.lisp4j.symbols.functions.MULFunction;
import com.github.beinn.lisp4j.symbols.functions.NOTFunction;
import com.github.beinn.lisp4j.symbols.functions.NUMERICEQFunction;
import com.github.beinn.lisp4j.symbols.functions.OPENFunction;
import com.github.beinn.lisp4j.symbols.functions.PROGNFunction;
import com.github.beinn.lisp4j.symbols.functions.READFunction;
import com.github.beinn.lisp4j.symbols.functions.READLINEFunction;
import com.github.beinn.lisp4j.symbols.functions.RETURNFunction;
import com.github.beinn.lisp4j.symbols.functions.SECONDFunction;
import com.github.beinn.lisp4j.symbols.functions.SQRTFunction;
import com.github.beinn.lisp4j.symbols.functions.SUMFunction;
import com.github.beinn.lisp4j.symbols.macros.Cond;
import com.github.beinn.lisp4j.symbols.macros.Defconstant;
import com.github.beinn.lisp4j.symbols.macros.Let;
import com.github.beinn.lisp4j.symbols.macros.Defmacro;
import com.github.beinn.lisp4j.symbols.macros.Defun;
import com.github.beinn.lisp4j.symbols.macros.Loop;
import com.github.beinn.lisp4j.symbols.macros.Setf;

/**
 *
 */
public class CommonLispPackage extends LispPackage {

	public CommonLispPackage(final Interpreter interpreter) {
		super("common-lisp");

    	// Add functions
        addFun(new NOTFunction());
        addFun(new LOADFunction(interpreter));
        addFun(new DistinctFunction());
        addFun(new MULFunction());
        addFun(new SUMFunction());
        addFun(new ErrorFunction());
        addFun(new CARFunction());
        addFun(new CLOSEFunction());
        addFun(new CDRFunction());
        addFun(new CONSFunction());
        addFun(new OPENFunction());
        addFun(new SECONDFunction());
        addFun(new PROGNFunction());
        addFun(new ATOMFunction());
        addFun(new SQRTFunction());
        addFun(new EQFunction());
        addFun(new NUMERICEQFunction());
        addFun(new LISTFunction());
        addFun(new HELPFunction(interpreter));
        addFun(new HaltFunction(interpreter));
        addFun(new READFunction());
        addFun(new READLINEFunction());
        addFun(new RETURNFunction());
        //Add symbols
        addSymbol(new PIConstant());
        
        //Add macros
        addMacro(new Defmacro(interpreter));
        addMacro(new Let(interpreter));
        addMacro(new Defun(interpreter));
        addMacro(new Cond(interpreter));
        addMacro(new Loop(interpreter));
        addMacro(new Setf(interpreter));
        addMacro(new Defconstant(interpreter));
        addMacro(new DEFPARAMETERFunction(interpreter));
    }

}
