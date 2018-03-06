package parser;

public interface GrammarSymbol {

    public boolean isAction();
    public int getIndex();
    public boolean isToken ();
    public boolean isNonTerminal();

    }
