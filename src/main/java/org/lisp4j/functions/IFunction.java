package org.lisp4j.functions;

import java.util.List;

public interface IFunction {

    String call(List<String> tokens);

    String getName();

}
