/**
 * @author Rose Clarfeld
 */
package lexer;
public class Token {
    public int Tokentag;
    public static String tokenType = " ";
    public String value = " ";
    public String[] tokens = new String[]{
            "PROGRAM",
            "BEGIN",
            "END",
            "VAR",
            "FUNCTION",
            "PROCEDURE",
            "RESULT",

            "INTEGER",
            "REAL",
            "ARRAY",

            "OF",
            "IF",
            "THEN",
            "ELSE",
            "WHILE",
            "DO",

            "NOT",

            "IDENTIFIER",
            "INTCONSTANT",
            "REALCONSTANT",

            "RELOP",
            "MULOP",
            "ADDOP",
            "ASSIGNOP",

            "COMMA",
            "SEMICOLON",
            "COLON",
            "RIGHTPAREN",
            "LEFTPAREN",
            "RIGHTBRACKET",
            "LEFTBRACKET",
            "UNARYMINUS",
            "UNARYPLUS",
            "DOUBLEDOT",
            "ENDMARKER",
            "ENDOFFILE",

    };


    public Token(int tag, String val) {
        tokenType = tagToString(tag);
        value = val;
        Tokentag = tag;
    }

    public String tagToString(int tag) {
        for (int i = 0; i < 37; i++) {
            if (tag == i) {
                return tokens[i];
            }
        }
        return tokens[1];
    }
}

