package parser;

import java.io.*;
import java.sql.*;
import java.util.*;
import lexer.*;


public class Parser {
    private static final int error = 999;
    public ParseTable parseTable = new ParseTable();
    private RHSTable rhsTable = new RHSTable();
    public int tokeType;
    public GrammarSymbol predicted;
    public TokenType tokenType;


    public void Parser() throws IOException, ParserError {
        rhsTable.init();
        tokeType = ParserMain.tokensStack.pop().Tokentag;
        Stack<GrammarSymbol> stack = new Stack<GrammarSymbol>();
        stack.push(TokenType.ENDOFFILE);
        stack.push(NonTerminal.Goal);
        TokenType[] tokenValues = TokenType.values();
        tokenType = tokenValues[tokeType];

        while (tokeType != TokenType.ENDOFFILE.getIndex()) {
            predicted = stack.pop();

            if (predicted.isNonTerminal()) {
                if (parseTable.getEntry(tokenType, predicted) == error) {
                    ParserError.UnexpectedToken(tokenType);
                } else {
                    GrammarSymbol[] rule = rhsTable.getRule(parseTable.getEntry(tokenType, predicted));
                    for (int i = rule.length-1; i >= 0; i--) {
                        stack.push(rule[i]);
                    }
                }
            }
            else if (predicted.isAction()) {
                tokeType = ParserMain.tokensStack.pop().Tokentag;
                tokenType = tokenValues[tokeType];
            }
            //predicted is a terminal
            else {
                if (predicted == tokenType) {
                    tokeType = ParserMain.tokensStack.pop().Tokentag;
                    tokenType = tokenValues[tokeType];
                } else {
                    //throw error "expecting x, y, found " " " //x is predicted y is current token
                    ParserError.UnexpectedValues(predicted, tokenType);
                }
            }
        }
        parseTable.printTable();
    }
}
