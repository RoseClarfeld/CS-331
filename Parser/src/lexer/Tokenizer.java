package lexer;

import java.io.*;
import java.util.*;

/**
 *
 */
public class Tokenizer {
    public static char currentChar;
    public static char charPlusOne;
    public static char charPlusTwo;
    public static int countE = 0;
    public static int stringLength = 0;
    public static int numLines = 0;
    public static int numChars = 0;
    public static String cLine = " ";
    public static int indexOfLineArray = 0;
    public static String[] currLine;
    public static int lengthLine = 0;
    private static final String VALID_CHARS =
            "+A*aBCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,;:<>/[]-=()}{\t0123456789\n \u0000";
    private static String Id;
    private static String num;
    private static int countPoint = 0;
    private static int lastToken = 0;
    private static InputStream is;
    //Hashtable to store reserved keywords
    private static Hashtable ids = new Hashtable<String, Integer>();

    private BufferedReader br;

    public static void calcNumLines(File file) throws IOException {
        // getting the number of lines to put into the array
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        try {

            byte[] c = new byte[1024];
            boolean empty = true;
            while ((numChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < numChars; ++i) {
                    if (c[i] == '\n') {
                        ++numLines;
                    }
                    empty = (c[numChars - 1] != '\n');
                }
                if (empty) {
                    ++numLines;
                }
            }
        } finally {
            is.close();
        }
    }

    public Tokenizer(File file) throws IOException {
        calcNumLines(file);
        //new buffered reader
        currLine = new String[numLines];
        FileReader fRead = new FileReader(file);
        BufferedReader bRead = new BufferedReader(fRead);
        //entering all the lines into an array
        String lineRead;
        for (indexOfLineArray = 0; indexOfLineArray < numLines; indexOfLineArray++) {
            lineRead = bRead.readLine();
            currLine[indexOfLineArray] = lineRead;
        }
        indexOfLineArray = 0;
        stringLength = 0;
        cLine = currLine[indexOfLineArray];
        lengthLine = cLine.length();

        //if current line has more than three characters
        if (cLine.length() >= 3) {
            currentChar = getNextChar();
            charPlusOne = getNextChar();
            charPlusTwo = getNextChar();
        }
        //if current line has only three characters
        if (3 == (cLine.length())) {
            currentChar = getNextChar();
            stringLength++;
            charPlusOne = getNextChar();
            stringLength++;
            charPlusTwo = getNextChar();
            //if more than just current line
            if (numLines - 1 > indexOfLineArray) {
                indexOfLineArray++;
                cLine = currLine[indexOfLineArray];
                stringLength = 0;
            }

        }

        //if current line has only two characters
        if (2 == (cLine.length())) {
            currentChar = getNextChar();
            charPlusOne = getNextChar();

            //if more than just current line
                charPlusTwo = getNextChar();
            }


        //if current line only has one character
        if (cLine.length() == 1) {
            currentChar = getNextChar();
            //if more than just current line
            if (numLines - 1 > indexOfLineArray) {
                indexOfLineArray++;
                cLine = currLine[indexOfLineArray];
                stringLength = 0;
                charPlusOne = getNextChar();
                stringLength++;
                charPlusTwo = getNextChar();
                stringLength = 2;
            }

        }

        if(cLine.length()==0){
            if(numLines-1> indexOfLineArray){
                indexOfLineArray++;
                cLine=currLine[indexOfLineArray];
                stringLength =0;
                currentChar= getNextChar();
                stringLength++;
                charPlusOne = getNextChar();
                stringLength++;
                charPlusTwo = getNextChar();
                stringLength++;
            }
        }
        lengthLine = currLine[indexOfLineArray].length();
    }

    private static char getNextChar() throws IOException {
        //cLine is a string that the current line goes into
        //string length is not getting properly tested because cline is already on a different line
        //length of line before = total of string length
        //length of cLine
        cLine = currLine[indexOfLineArray];
char c =' ';
        if (lengthLine -1 == stringLength) {
            c = cLine.charAt(stringLength);
            stringLength = 0;
            if (numLines - 1 > indexOfLineArray) {
                indexOfLineArray++;
                cLine = currLine[indexOfLineArray];
                lengthLine=cLine.length();
            }
            if (isWhiteSpace(c)) {
                    c = '\u0000';
                    return c;
                }
                return c;
            }



        if (cLine.length() == 0) {
            if (numLines - 1 > indexOfLineArray) {
                indexOfLineArray++;
                cLine = currLine[indexOfLineArray];
                lengthLine = cLine.length();
                stringLength = 0;
                c = cLine.charAt(stringLength);
                stringLength++;
                return c;
            }
            // while (c == '\n' || c == '\r') {
            //   indexOfLineArray++;
            // cLine = currLine[indexOfLineArray];
            // stringLength = 0;
            // c = cLine.charAt(0);
            c = '\u0000';
            return c;
        } else {
            c = cLine.charAt(stringLength);
            stringLength++;
            /*if(cLine.charAt((stringLength))=='\n'){
                indexOfLineArray++;
                cLine=currLine[indexOfLineArray];
                stringLength=0;
                c=cLine.charAt(0);*/
            if (isWhiteSpace(c)) {
                c = '\u0000';
                return c;
            }

            return c;
        }
    }

    /*
        c =cLine.charAt((stringLength));
    //if cLine is empty
        if (c=='\n'||c=='\r'){
            //go to the next line
            indexOfLineArray++;
            //cline is updated
            cLine = currLine[indexOfLineArray];
            //
            stringLength=0;
            c=cLine.charAt((stringLength));
        } else {
            stringLength++;
        }
        return c;
    }*/

    //reserving keywords in the hashtable
    private static void reserve(Word t) {
        ids.put(t.lexeme, t);
    }

    //reserving keywords to put in the hashtable
    public void Lex() {
        reserve(new Word("PROGRAM", Tag.PROGRAM));
        reserve(new Word("BEGIN", Tag.BEGIN));
        reserve(new Word("END", Tag.END));
        reserve(new Word("VAR", Tag.VAR));
        reserve(new Word("FUNCTION", Tag.FUNCTION));
        reserve(new Word("PROCEDURE", Tag.PROCEDURE));
        reserve(new Word("RESULT", Tag.RESULT));
        reserve(new Word("INTEGER", Tag.INTEGER));
        reserve(new Word("REAL", Tag.REAL));
        reserve(new Word("ARRAY", Tag.ARRAY));
        reserve(new Word("OF", Tag.OF));
        reserve(new Word("IF", Tag.IF));
        reserve(new Word("THEN", Tag.THEN));
        reserve(new Word("ELSE", Tag.ELSE));
        reserve(new Word("WHILE", Tag.WHILE));
        reserve(new Word("DO", Tag.DO));
        reserve(new Word("NOT", Tag.NOT));
        reserve(new Word("IDENTIFIER", Tag.IDENTIFIER));
        reserve(new Word("INTCONSTANT", Tag.INTCONSTANT));
        reserve(new Word("REALCONSTANT", Tag.REALCONSTANT));
        reserve(new Word("RELOP", Tag.RELOP));
        reserve(new Word("MULOP", Tag.MULOP));
        reserve(new Word("ADDOP", Tag.ADDOP));
        reserve(new Word("OR", Tag.ADDOP));
        reserve(new Word("DIV", Tag.MULOP));
        reserve(new Word("MOD", Tag.MULOP));
        reserve(new Word("AND", Tag.MULOP));
        reserve(new Word("ASSIGNOP", Tag.ASSIGNOP));
        reserve(new Word("COMMA", Tag.COMMA));
        reserve(new Word("SEMICOLON", Tag.SEMICOLON));
        reserve(new Word("COLON", Tag.COLON));
        reserve(new Word("RIGHTPAREN", Tag.RIGHTPAREN));
        reserve(new Word("LEFTPAREN", Tag.LEFTPAREN));
        reserve(new Word("RIGHTBRACKET", Tag.RIGHTBRACKET));
        reserve(new Word("LEFTBRACKET", Tag.LEFTBRACKET));
        reserve(new Word("UNARYMINUS", Tag.UNARYMINUS));
        reserve(new Word("UNARYPLUS", Tag.UNARYPLUS));
        reserve(new Word("DOUBLEDOT", Tag.DOUBLEDOT));
        reserve(new Word("ENDMARKER", Tag.ENDMARKER));
        reserve(new Word("ENDOFFILE", Tag.ENDOFFILE));
    }

    private static Boolean isValid(char c) {
        int i;
     /*   char valid[] = new char[VALID_CHARS.length()];
        for(i = 0; i <VALID_CHARS.length(); i++){
            valid[i] = VALID_CHARS.charAt(i);
        }*/
        boolean result = false;
        for (i = 0; i < VALID_CHARS.length(); i++) {

            if (c == VALID_CHARS.charAt(i)) {
                result = true;
                return result;
            }
        }
        return result;
    }

    private static Boolean isLetter(char c) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int i;
        for (i = 0; i < alphabet.length(); i++) {
            if (c == alphabet.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isNumber(char c) {
        String numbers = "1234567890";
        int i;
        for (i = 0; i < numbers.length(); i++) {
            char a = numbers.charAt(i);
            if (c == a) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isSymbol(char c) {
        String symbols = ".,;:<>/*[]+-=()}{\t ";
        int i;
        for (i = 0; i < symbols.length(); i++) {
            char a = symbols.charAt(i);
            if (c == a) {
                return true;
            }
        }
        return false;
    }


    public static boolean isWhiteSpace(char c) {
        if (Character.isWhitespace(c)) {
            return true;
        }
        return false;
    }

    public static Token getNextToken() throws IOException {
        int number = 0;
        String str = Character.toString(currentChar);
        if (currentChar != '\u0000') {
            if (isValid(currentChar)) {
                //{comment
                if (lastToken == Tag.RIGHTBRACKET) {
                    while (currentChar != '}' && (currentChar != '\u0000')) {
                        //if reached end of string
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (currentChar == '\u0000') {
                        setLastToken(Tag.ENDOFFILE);
                        LexicalError.BadComment(indexOfLineArray, currentChar);
                        return (new Token(lastToken, ""));
                    }
                    //must equal leftbracket
                    setLastToken(Tag.LEFTBRACKET);
                    currentChar = charPlusOne;
                    charPlusOne = charPlusTwo;
                    charPlusTwo = getNextChar();
                    return (new Token(lastToken, ""));
                }
                switch (currentChar) {
                    case '=':
                        setLastToken(Tag.RELOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();

                        return (new Token(lastToken, "1"));
                    case '<':
                        //<>
                        if (charPlusOne == '>') {
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "2"));
                        }
                        //<=
                        if (charPlusOne == '=') {
                            setLastToken(Tag.RELOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "5"));
                        }
                        //<
                        setLastToken(Tag.RELOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "3"));

                    case '>':
                        //>=
                        if (charPlusOne == '=') {
                            setLastToken(Tag.RELOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "6"));
                        }
                        setLastToken(Tag.RELOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "4"));
                    case '+':
                        if (getLastToken() == Tag.RIGHTPAREN || getLastToken() == Tag.RIGHTBRACKET || getLastToken() == Tag.IDENTIFIER || getLastToken() == Tag.INTCONSTANT || getLastToken() == Tag.REALCONSTANT) {

                            setLastToken(Tag.ADDOP);
                            //if (stringLength == (cLine.length())) {
                            //  indexOfLineArray++;
                            //cLine = currLine[indexOfLineArray];
                            //stringLength = 0;
                            //}
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "1"));
                        }
                        setLastToken(Tag.UNARYPLUS);
                        //if (stringLength == (cLine.length())) {
                        //  indexOfLineArray++;
                        //cLine = currLine[indexOfLineArray];
                        //stringLength = 0;
                        // }

                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));


                    case '-':
                        if (getLastToken() == Tag.RIGHTPAREN || getLastToken() == Tag.RIGHTBRACKET || getLastToken() == Tag.IDENTIFIER || getLastToken() == Tag.INTCONSTANT || getLastToken() == Tag.REALCONSTANT) {

                            setLastToken(Tag.ADDOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "2"));
                        } else {
                            setLastToken(Tag.UNARYMINUS);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }

                    case '*':

                        setLastToken(Tag.MULOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "1"));

                    case '/':

                        setLastToken(Tag.MULOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "2"));


                    case '{':
                        setLastToken(Tag.RIGHTBRACKET);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case '}':

                        setLastToken(Tag.LEFTBRACKET);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case ',':

                        setLastToken(Tag.COMMA);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case ';':

                        setLastToken(Tag.SEMICOLON);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case ':':

                        setLastToken(Tag.COLON);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case '(':

                        setLastToken(Tag.RIGHTPAREN);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case ')':

                        setLastToken(Tag.LEFTPAREN);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case '.':
                        if (charPlusOne == '.') {
                            setLastToken(Tag.DOUBLEDOT);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }
                        setLastToken(Tag.ENDMARKER);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                }


                if (isLetter(currentChar)) {
                    Id = "";

                    //figure out how to skip whitespace
                    int line = indexOfLineArray;
                    while (line == indexOfLineArray && !isSymbol(currentChar) && Id.length() < 250) {
                        int lengthID = Id.length();
                        Id = Id + currentChar;
                        Id = Id.toUpperCase();
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (Id.length() >= 250) {
                        LexicalError.TooLongID(indexOfLineArray, currentChar);
                        return (new Token(lastToken, ""));
                    }
                    //must have read a full id WHERE IS CONTAINS KEY
                    if (ids.containsKey(Id)) {
                        if (Id == "OR") {
                            setLastToken(Tag.ADDOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "3"));
                        }
                        if (Id == "DIV") {
                            setLastToken(Tag.MULOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "3"));
                        }
                        if (Id == "MOD") {
                            setLastToken(Tag.MULOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "4"));
                        }
                        if (Id == "AND") {
                            setLastToken(Tag.MULOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "5"));
                        }
                        setLastToken(Tag.IDENTIFIER);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, Id));

                    } else {
                        reserve(new Word(Id, ids.size() + 1));
                        setLastToken(Tag.IDENTIFIER);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, Id));
                    }
                }


                //if peek is a number
                if (isNumber(currentChar)) {
                    num = " ";
                    while (!isWhiteSpace(currentChar) && num.length() < 250) {
                        //if current char is a letter
                        if (isLetter(currentChar)) {
                            if (currentChar != 'e' && currentChar != 'E') {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }
                                setLastToken(Tag.INTCONSTANT);
                                stringLength++;
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));

                            }
                            //currchar = e
                            if (isLetter(charPlusOne)) {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }
                                setLastToken(Tag.INTCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));


                            }
                            //this is wrong
                            if (countE > 1) {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                }
                                stringLength++;
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }

                            setLastToken(Tag.INTCONSTANT);
                            stringLength++;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        countE++;
                        num = num + currentChar;
                        stringLength++;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (isSymbol(currentChar)) {
                        if (currentChar != '.') {
                            if (countPoint == 1) {

                                setLastToken(Tag.REALCONSTANT);
                                stringLength++;
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));

                            }

                            setLastToken(Tag.INTCONSTANT);
                            stringLength++;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        //so currChar=.
                        if (countPoint == 1) {

                            setLastToken(Tag.REALCONSTANT);
                            stringLength++;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        //not already a dot
                        if (charPlusOne == '.') {

                            setLastToken(Tag.DOUBLEDOT);
                            stringLength++;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }
                        //so char is a dot but just normal
                        num = num + currentChar;
                        stringLength++;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    //current char is a number
                    if (charPlusOne == '.' && !isNumber(charPlusTwo)) {
                        num = num + currentChar;
                        if (countPoint == 1) {

                            setLastToken(Tag.REALCONSTANT);
                            stringLength++;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }

                        setLastToken(Tag.INTCONSTANT);
                        stringLength++;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    }
                    if ((charPlusOne == 'e' || charPlusOne == 'E') && !isNumber(charPlusTwo)) {
                        num = num + currentChar;
                        if (countPoint == 1) {

                            setLastToken(Tag.REALCONSTANT);
                            stringLength++;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }

                        setLastToken(Tag.INTCONSTANT);
                        stringLength++;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    }
                    num = num + currentChar;
                    stringLength++;
                    currentChar = charPlusOne;
                    charPlusOne = charPlusTwo;
                    charPlusTwo = getNextChar();

                    if (num.length() >= 250) {
                        LexicalError.TooLongID(indexOfLineArray, currentChar);
                        //
                        return (new Token(lastToken, ""));
                    }
                    if (countPoint == 0) {

                        setLastToken(Tag.INTCONSTANT);
                        stringLength++;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    } else {

                        setLastToken(Tag.INTCONSTANT);
                        stringLength++;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));

                    }


                }

            }
            LexicalError.InvalidInput(indexOfLineArray, currentChar);
            return (new Token(lastToken, ""));

        }
        setLastToken(Tag.ENDOFFILE);
        return (new Token(lastToken, ""));
    }




    public static void setLastToken(int tag) {

        lastToken = tag;
    }

    public static int getLastToken() {

        return lastToken;
    }
}
