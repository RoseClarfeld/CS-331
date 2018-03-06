package parser;

import lexer.*;

public class Terminal implements GrammarSymbol{

    private static TokenType type;

    public Terminal (TokenType type) {
        this.type = type;
    }

    public static TokenType getType() {
        return type;
    }

    @Override
    public boolean isAction() {
        return false;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public boolean isToken() {
        return false;
    }

    @Override
    public boolean isNonTerminal() {
        return false;
    }
}
