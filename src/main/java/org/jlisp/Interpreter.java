package org.jlisp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author elvis
 *
 */
public class Interpreter {

    private Map<String, IFunction> functions = new HashMap<String, IFunction>();


    public Interpreter() {
        loadFunctions();
    }
    
    private void loadFunctions() {
        addFun(new MULFunction());
        addFun(new SUMFunction());
    }

    private void addFun(final IFunction function) {
        functions.put(function.getName(), function);
    }

    /**
     * 
     * @param code
     * @return
     */
    public List<String> execute(final String code) {
        EnumState state = EnumState.START;
        StringBuilder buffer = new StringBuilder();
        List<String> tokens = null;
        List<List<String>> lists = new ArrayList<List<String>>();
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '(') {
                newToken(buffer, tokens);
                tokens = new ArrayList<String>();
                lists.add(tokens);
            } else if (c == ')') {
                newToken(buffer, tokens);
                String token = process(tokens);
                lists.remove(tokens);

                if (lists.size() > 0) {
                    tokens = lists.get(lists.size() - 1);
                    tokens.add(token);
                } else {
                    results.add(token);
                }
            } else if (c == '`' || c == '\'') {
                newToken(buffer, tokens);
            } else if (c == ' ') {
                newToken(buffer, tokens);
            } else {
                buffer.append(c);
            }
        }

        return results;
    }

    private void newToken(StringBuilder buffer, List<String> tokens) {
        if (buffer.length() > 0) {
            tokens.add(buffer.toString());
            buffer.setLength(0);
        }
    }

    private String process(final List<String> tokens) {
        if (tokens.isEmpty()) {
            return null;
        }

        if (tokens.get(0).equals("defun")) {
            String funName = tokens.get(1).toUpperCase();
            String params = tokens.get(2);
            String function = tokens.get(3);
            functions.put(funName, new Function(funName, params, function));
            return funName;
        }

        String funName = tokens.get(0).toUpperCase();
        IFunction function = functions.get(funName);
        if (function != null) {
            return function.call(tokens);
        }

        throw new UnsupportedOperationException(tokens.toString());
    }
}
