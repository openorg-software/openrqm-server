/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package utils;

import java.math.BigInteger;

/**
 * Utilities for calculating the rank of an element.
 */
public class RankUtils {

    public static final BigInteger NEW_ELEMENTS = new BigInteger("19928148895209409152"); //26^20 : 10^9
    public static final BigInteger MAX_ELEMENTS = new BigInteger("19928148895209409152340197376"); //26^20
    public static final int MAX_LENGTH = 29;
    
    // lookup table 'a'-'z' maps to '0'-'p'
    private static final char[] BASE26 = new char[123]; // 97 to 122 ('a' to 'z') as inputs
    static {
        BASE26['a'] = '0';
        BASE26['b'] = '1';
        BASE26['c'] = '2';
        BASE26['d'] = '3';
        BASE26['e'] = '4';
        BASE26['f'] = '5';
        BASE26['g'] = '6';
        BASE26['h'] = '7';
        BASE26['i'] = '8';
        BASE26['j'] = '9';
        BASE26['k'] = 'a';
        BASE26['l'] = 'b';
        BASE26['m'] = 'c';
        BASE26['n'] = 'd';
        BASE26['o'] = 'e';
        BASE26['p'] = 'f';
        BASE26['q'] = 'g';
        BASE26['r'] = 'h';
        BASE26['s'] = 'i';
        BASE26['t'] = 'j';
        BASE26['u'] = 'k';
        BASE26['v'] = 'l';
        BASE26['w'] = 'm';
        BASE26['x'] = 'n';
        BASE26['y'] = 'o';
        BASE26['z'] = 'p';
    }

    // reverse lookup table '0'-'p' maps to 'a'-'z'
    private static final char[] REVERSE_BASE26 = new char[113]; // we need 48 to 57 ('0' to '9') and 97 to 112 ('a' to 'p') as inputs
    static {
        REVERSE_BASE26['0'] = 'a';
        REVERSE_BASE26['1'] = 'b';
        REVERSE_BASE26['2'] = 'c';
        REVERSE_BASE26['3'] = 'd';
        REVERSE_BASE26['4'] = 'e';
        REVERSE_BASE26['5'] = 'f';
        REVERSE_BASE26['6'] = 'g';
        REVERSE_BASE26['7'] = 'h';
        REVERSE_BASE26['8'] = 'i';
        REVERSE_BASE26['9'] = 'j';
        REVERSE_BASE26['a'] = 'k';
        REVERSE_BASE26['b'] = 'l';
        REVERSE_BASE26['c'] = 'm';
        REVERSE_BASE26['d'] = 'n';
        REVERSE_BASE26['e'] = 'o';
        REVERSE_BASE26['f'] = 'p';
        REVERSE_BASE26['g'] = 'q';
        REVERSE_BASE26['h'] = 'r';
        REVERSE_BASE26['i'] = 's';
        REVERSE_BASE26['j'] = 't';
        REVERSE_BASE26['k'] = 'u';
        REVERSE_BASE26['l'] = 'v';
        REVERSE_BASE26['m'] = 'w';
        REVERSE_BASE26['n'] = 'x';
        REVERSE_BASE26['o'] = 'y';
        REVERSE_BASE26['p'] = 'z';
    }
    
    /**
     * Calculates the middle rank between smallRank and bigRank.
     * 
     * @param smallRank the smaller one of the two ranks
     * @param bigRank the bigger one of the two rank
     * @param maxLength the maximal length of characters
     * @return the middle rank or null if no more space and maxLength reached
     */
    public String calculateMiddleRank(String smallRank, String bigRank, int maxLength) {
        //make both the same length (add a's to the right of the shorter one)
        if (smallRank.length() > bigRank.length()) {
            bigRank = addARight(bigRank, smallRank.length());
        } else if (bigRank.length() > smallRank.length()) {
            smallRank = addARight(smallRank, bigRank.length());
        }
        //convert small rank to integer
        String smallRankBase26 = convert(smallRank, BASE26);
        BigInteger smallRankInteger = new BigInteger(smallRankBase26, 26);
        //convert big rank to integer
        String bigRankBase26 = convert(bigRank, BASE26);
        BigInteger bigRankInteger = new BigInteger(bigRankBase26, 26);
        //add both
        BigInteger sum = smallRankInteger.add(bigRankInteger);
        //divide by two
        BigInteger half = sum.divide(new BigInteger("2"));
        //convert back to rank with same length (add a's to the left)
        String rank = convert(half.toString(26), REVERSE_BASE26);
        String paddedRank = addALeft(rank, smallRank.length());
        //if padded Rank equals smallRank (because rounded down),
        //add an 'a' to the right of smallRank and bigRank and recalculate
        if (paddedRank.equals(smallRank)) {
            if (paddedRank.length() == maxLength) {
                return null;
            }
            paddedRank = calculateMiddleRank(smallRank + 'a', bigRank + 'a', maxLength);
        }
        return paddedRank;
    }

    /**
     * Converts a string with the help of a lookup table.
     * 
     * Assumes the length of smallRank is equal to the length
     * of the bigRank.
     * 
     * @param s the original string
     * @param lookupTable the lookup table for convertion
     * @return the converted string
     */
    private String convert(String s, char[] lookupTable) {
        char[] c = s.toCharArray();
        String r = "";
        for (int i = 0; i < c.length; i++) {
            r += lookupTable[c[i]];
        }
        return r;
    }

    /**
     * Adds 'a' to the left of a string until length is reached.
     * 
     * @param s the original string
     * @param length the length that has to be reached
     * @return the padded string
     */
    private String addALeft(String s, int length) {
        int paddingLength = length - s.length();
        for (int i = 0; i < paddingLength; i++) {
            s = 'a' + s;
        }
        return s;
    }

    /**
     * Adds 'a' to the right of a string until length is reached.
     * 
     * @param s the original string
     * @param length the length that has to be reached
     * @return the padded string
     */
    private String addARight(String s, int length) {
        int paddingLength = length - s.length();
        for (int i = 0; i < paddingLength; i++) {
            s += 'a';
        }
        return s;
    }

    /**
     * Calculates newRank by adding newElements to oldRank.
     * 
     * @param oldRank the old rank
     * @param newElements the amount of new elements to add
     * @param maxElements the maximal length
     * @return the new rank or null if there is no more new space
     */
    public String calculateNewRank(String oldRank, BigInteger newElements, BigInteger maxElements) {
        //convert rank to integer
        String oldRankBase26 = convert(oldRank, BASE26);
        BigInteger oldRankInteger = new BigInteger(oldRankBase26, 26);
        //add gap
        BigInteger newRankInteger = oldRankInteger.add(newElements);
        if (newRankInteger.compareTo(maxElements) > 0) {
            return null;
        }
        //convert back to rank
        String newRank = convert(newRankInteger.toString(26), REVERSE_BASE26);
        String paddedNewRank = addALeft(newRank, oldRank.length());
        return paddedNewRank;
    }
}
