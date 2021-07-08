package com.sample.textsearch;

import java.math.BigInteger;
import java.util.Random;


public class PatternSearch {
    private String pat;      // the pattern  // needed only for Las Vegas
    private long patHash;    // pattern hash value
    private int M;           // pattern length
    private long Q;          // a large prime, small enough to avoid long overflow
    private int R;           // radix
    private long RM;         // R^(M-1) % Q

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R the alphabet size
     */
    public PatternSearch(char[] pattern, int R) {
        throw new UnsupportedOperationException("Operation not supported yet");
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public PatternSearch(String pat) {
        this.pat = pat;      // save pattern (needed only for Las Vegas)
        R = 256;
        M = pat.length();
        Q = longRandomPrime();

        RM = 1;
        for (int i = 1; i <= M-1; i++)
            RM = (R * RM) % Q;
        patHash = hash(pat, M);
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private boolean check(String txt, int i) {
        for (int j = 0; j < M; j++)
            if (pat.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }

    private boolean check(int i) {
        return true;
    }

    public int search(String txt) {
        int N = txt.length();
        if (N < M) return N;
        long txtHash = hash(txt, M);

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;

            // match
            int offset = i - M + 1;
            if ((patHash == txtHash) && check(txt, offset))
                return offset;
        }

        // no match
        return N;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * e.g. java PatternSearch rab abacadabrabracabracadabrabrabracad
     */
    public static void main(String[] args) {

        //String pat = args[0];
        //String txt = args[1];
        String pat = null;
        String txt = "abacadabrabracabracadabrabrabracad";

        PatternSearch searcher = new PatternSearch(pat);
        int offset = searcher.search(txt);

        System.out.println("offset:" + offset);
    }
}