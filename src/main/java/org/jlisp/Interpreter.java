package org.jlisp;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author elvis
 *
 */
public class Interpreter {

    /**
     * 
     * @param code
     * @return
     */
    public String execute(final String code) {
        EnumState state = EnumState.START;
        StringBuilder buffer = new StringBuilder();
        List<String> tokens = null;
        List lists = new ArrayList();
        //lists.add("");
        for(int i = 0; i < code.length() ; i++) {
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
                    Object obj = lists.get(lists.size() - 1);
                    tokens = (List) obj;
                    tokens.add(token);
                } else {
                    lists.add(token);
                }
            } else if (c == ' ') {
                newToken(buffer, tokens);
            } else {
                buffer.append(c);
            };
        }
        return lists.get(0).toString();
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
        
        if (tokens.get(0).equals("+")) {
            double acum = 0;
            for (int i = 1; i < tokens.size(); i++) {
                acum += Double.parseDouble(tokens.get(i));
            }
            return String.valueOf(acum); 
        }
        
        if (tokens.get(0).equals("*")) {
            double acum = 1;
            for (int i = 1; i < tokens.size(); i++) {
                acum *= Double.parseDouble(tokens.get(i));
            }
            return String.valueOf(acum); 
        }
        
        throw new UnsupportedOperationException();
    }
}
