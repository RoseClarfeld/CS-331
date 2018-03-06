package parser;

public class ParserError extends Exception {
    public static void UnexpectedToken(TokenType token) {
        System.out.println("Error: \nUnexpected token: " + token);
        Runtime.getRuntime().halt(0);
    }
    public static void UnexpectedValues(GrammarSymbol g, TokenType token ){
        System.out.println("Error: \nUnexpected values found: " +g+" "+token);
    }
}
