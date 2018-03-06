package parser;

import lexer.LexicalAnalyzer;
import lexer.Tag;
import lexer.Token;
import lexer.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ParserMain {
    public static Stack<Token> tokensStack = new Stack<Token>();
    public static List<Token> tokensList;
    public static Token token = new Token(0, " ");
    public static Token eof = new Token (Tag.ENDOFFILE, "");
    public static void main(String[] args) throws IOException {
        //intialize
        File file = new File("textfile.txt");
        Tokenizer tokenizer = new Tokenizer(file);

        while (tokenizer.getNextToken() != eof) {
            token = tokenizer.getNextToken();
            tokensList.add(token);
        }
        if (tokenizer.getNextToken() == eof) {
            token = (new Token(Tag.ENDOFFILE, ""));
            tokensList.add(token);
        }
        Collections.reverse(tokensList);
        for (int i = 0; i < tokensList.size(); i++) {
            tokensStack.push(tokensList.get(i));
        }
        Parser parser = new Parser();
        Runtime.getRuntime().halt(0);

    }
}
