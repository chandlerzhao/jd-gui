/*
 * Copyright (c) 2008-2015 Emmanuel Dupuy
 * This program is made available under the terms of the GPLv3 License.
 */

package org.jd.gui.util.parser.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ANTLRJavaParser {

    public static void parse(CharStream input, JavaListener listener) {
        try {
            JavaLexer lexer = new JavaLexer(input);

            lexer.removeErrorListeners();

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokens);

            parser.removeErrorListeners();

            ParseTree tree = parser.compilationUnit();

            ParseTreeWalker.DEFAULT.walk(listener, tree);
        } catch (StackOverflowError ignore) {
            // Too complex source file, probably not written by a human.
            // This error may happen on Java file generated by ANTLR for example.
        }
    }
}
