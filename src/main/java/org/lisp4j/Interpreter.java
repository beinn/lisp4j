package org.lisp4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lisp4j.ast.ATOM;
import org.lisp4j.ast.LIST;
import org.lisp4j.functions.HaltFunction;
import org.lisp4j.functions.IFunction;
import org.lisp4j.functions.MULFunction;
import org.lisp4j.functions.SUMFunction;

/**
 * 
 * @author elvis
 *
 */
public class Interpreter {

    public Map<String, IFunction> functions = new HashMap<String, IFunction>();
    private boolean halted = false;

    public Interpreter() {
        loadFunctions();
    }

    private void loadFunctions() {
        addFun(new MULFunction());
        addFun(new SUMFunction());
        addFun(new HaltFunction(this));
    }

    private void addFun(final IFunction function) {
        functions.put(function.getName(), function);
    }

    private EnumState state = EnumState.START;

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
                } else {
                    buffer.append(c);
                }
            }
        }

/*        ArrayList<String> r = new ArrayList<String>();
        r.add(root.process(this, true).toString());*/
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
