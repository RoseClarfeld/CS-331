package parser;

import lexer.Tag;
import lexer.Token;
import lexer.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ParserMain {
    public static Stack<Token> tokensStack = new Stack<Token>();
    public static List<Token> tokensList = new List<Token>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Token> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Token token) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Token> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Token> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Token get(int index) {
            return null;
        }

        @Override
        public Token set(int index, Token element) {
            return null;
        }

        @Override
        public void add(int index, Token element) {

        }

        @Override
        public Token remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Token> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Token> listIterator(int index) {
            return null;
        }

        @Override
        public List<Token> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    public static Token token = new Token(0, " ");
    public static Token eof = new Token (Tag.ENDOFFILE, "");
    public static void main(String[] args) throws IOException {
        //intialize
        File file = new File("errorFile.txt");
        Tokenizer tokenizer = new Tokenizer(file);

        while (tokenizer.getNextToken().Tokentag != Tag.ENDOFFILE) {
            token = tokenizer.getNextToken();
            System.out.println(token.tagToString(token.Tokentag));
            tokensList.add(token);
        }
        if (tokenizer.getNextToken().Tokentag != Tag.ENDOFFILE) {
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
