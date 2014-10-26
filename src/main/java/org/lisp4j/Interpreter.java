package org.lisp4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.symbols.ISymbol;
import org.lisp4j.symbols.constants.PIConstant;
import org.lisp4j.symbols.functions.CARFunction;
import org.lisp4j.symbols.functions.CDRFunction;
import org.lisp4j.symbols.functions.CLOSEFunction;
import org.lisp4j.symbols.functions.CONSFunction;
import org.lisp4j.symbols.functions.DEFPARAMETERFunction;
import org.lisp4j.symbols.functions.ErrorFunction;
import org.lisp4j.symbols.functions.HELPFunction;
import org.lisp4j.symbols.functions.HaltFunction;
import org.lisp4j.symbols.functions.MULFunction;
import org.lisp4j.symbols.functions.OPENFunction;
import org.lisp4j.symbols.functions.SUMFunction;

/**
 * 
 * @author elvis
 *
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
        addFun(new MULFunction());
        addFun(new SUMFunction());
        addFun(new ErrorFunction());
        addFun(new CARFunction());
        addFun(new CLOSEFunction());
        addFun(new DEFPARAMETERFunction(this));
        addFun(new PIConstant());
        addFun(new CDRFunction());
        addFun(new CONSFunction());
        addFun(new OPENFunction());
        addFun(new HELPFunction(this));
        addFun(new HaltFunction(this));
    }

    private void addFun(final ISymbol function) {
        for(final String name:function.getNames()){
            functions.put(name, function);
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
