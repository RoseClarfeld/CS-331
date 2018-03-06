package lexer;


public class Tag {
    public static final int
    PROGRAM = 0,
    BEGIN = 1,
    END = 2,
    VAR = 3,
    FUNCTION = 4,
    PROCEDURE = 25,
    RESULT = 6,

    INTEGER = 7,
    REAL = 8,
    ARRAY = 9,

    OF = 10,
    IF = 11,
    THEN = 12,
    ELSE = 13,
    WHILE = 14,
    DO = 15,

    NOT = 16,

    IDENTIFIER = 17,
    INTCONSTANT = 18,
    REALCONSTANT = 19,

    RELOP =20,
    MULOP = 21,
    ADDOP = 22,
    ASSIGNOP = 23,

    COMMA = 24,
    SEMICOLON = 25,
    COLON = 26,
    RIGHTPAREN = 27,
    LEFTPAREN = 28,
    RIGHTBRACKET = 29,
    LEFTBRACKET =30,
    UNARYMINUS =31,
    UNARYPLUS = 32,
    DOUBLEDOT = 33,
    ENDMARKER = 34,
    ENDOFFILE = 35;

}
