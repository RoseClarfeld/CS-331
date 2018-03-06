package parser;
import java.io.*;
import java.util.*;
import java.net.*;


public class ParseTable {

    private int nRows = 35;
    private int nColumns = 38;

    public int[][] parseTable ={
            {1, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 65, 999},
            {999, 999,  -6, -16, 25, 999, 999, 999,  -9, 999, 999, 999, 999, 999, 999, 999, 26, 29, 999, 35, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, -28, 999, 999,  33,  37,  39,  41, 999, 999, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 5, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999,  -6, 15, 999, 999, 999, 999,  -9, 999, 999, 17, 18, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999,  -6, 15, 999, 999, 999, 999,  -9, 999, 999, 17, 19, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999 ,999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 10, 999, 12, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 10, 999, 13, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 11, 999, 999, 14, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 26, 30, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, 999, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 32,  37,  39,  41, 999, 999, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 26, 31, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, 999, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 45, 999, 999, 999, 999, 42, 999, 48, 999, 52, 999, 999, 58, 999, 999, 999, 999, 999},
            {999, 2, 999, 999, 999, 999, 7, 999, 8, 999, 999, 999, 999, 999, 22, 999, 26, 29, 999, 34, 45, 999, 999, 999, 999, 42, 999, 48, 999, 52, 999, 999, 55, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 45, 999, 999, 999, 999, 42, 999, 48, 999, 52, 999, 999, 56, 999, 999, 999, 999, 66},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 45, 999, 999, 999, 999, 42, 999, 48, 999, 52, 999, 999, 56, 999, 999, 999, 999, 67},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, 999, 999, 46, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, 999, 999, 999, 999, 999, 999, 999, 53,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, 999, 999, 999, 999, 50, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 36,  39, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 3, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, 43, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, -21, 999, 23, 999, 999, 27, 999, 999,  33,  37,  39, 41, 999, 999, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999,  -4, 999, 999, 999, 999, 999, 999, 999, -21, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999,  -4, 999, 999, 999, 999, 999, 999, 999, 999, 999, -24, 999, 999, 999, 999, 999, 999, 999,  39, 999, 999, -44, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999,999,999, 999, 999, 999, 999, 20, 999, 999, 999, 999, 999, 999, 45, 999, 37, 999, 40, 42, 999, 48, 999, 52, 999, 999, 57, 999, 59, 61, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999 ,999, 999, 999, 999, 999, 999,  39, 999, 999 ,999, 999, -47, 999, -51, 999, 999, -54,  60,  62, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999 ,999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 36, 38, 999, 999, 999 ,999 ,999, 999, 999, 999, 999, 999, 60, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 45, 999, 999, 999, 999, 42, 999, 49, 999, 999, 999, 64, 999, 999, 999, 999, 999 ,999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 45, 999, 999, 999, 999, 42, 999, 49, 999, 999, 999, 63, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
            {999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999 ,999, 999, 999, 999, 999, 999, 999}
    };

    public int getEntry (TokenType t, GrammarSymbol n)
    {
        return parseTable[t.getIndex()][n.getIndex()];
    }

    public void printTable()
    {
        for ( int i = 0; i < nRows; i++ )
        {
            for (int j = 0; j < nColumns; j++ )
                System.out.print(" " + parseTable[i][j]);
            System.out.println();
        }
    }

}

