package org.jlisp;

import java.util.List;

public interface IFunction {

    String call(List<String> tokens);

    String getName();

}
