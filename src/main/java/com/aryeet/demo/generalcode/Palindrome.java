package com.aryeet.demo.generalcode;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    public static void main(String[] args) {
        /**
         * The following checks are for integer Palindrome
         */
        if(checkPalindromeInteger(1, 5).size() > 0){
            System.out.println(checkPalindromeInteger(1, 1000).toString());
        }
        else{
            System.out.println("There is no element in Palindrome");

        }
        /**
         * The following checks are for string Palindrome
         */
        checkPalindromeString("Radar");
        checkPalindromeString("aaabbbbaaa");
        checkPalindromeString("tenet");
        checkPalindromeString("shyam");
        checkPalindromeString("tenet tenet");
        checkPalindromeString("tenet   tenet");
        //checkPalindromeString("   ");


    }

    private static void checkPalindromeString(String inputStringToCheckPalindrome) {
        if (inputStringToCheckPalindrome.trim().isEmpty()) {
            throw new RuntimeException("invalid String to evaluate");
        }

        StringBuilder reverseSB = new StringBuilder("");

        for (int i = (inputStringToCheckPalindrome.length() - 1); i >= 0; --i) {
            reverseSB.append(inputStringToCheckPalindrome.charAt(i));
        }

        if (inputStringToCheckPalindrome.toLowerCase().equals(reverseSB.toString().toLowerCase())) {
            System.out.println(inputStringToCheckPalindrome + " is a Palindrome String.");
        } else {
            System.out.println(inputStringToCheckPalindrome + " is not a Palindrome String.");
        }
    }

    private static List<Integer> checkPalindromeInteger(Integer startingNumber, Integer endNumber) {

        List<Integer> palindromeNumbers = new ArrayList<>();

        for (int i = startingNumber; i <= endNumber; i++) {
            int remainder;
            int reversedNum = 0;
            int numToEvaluate = i;
            int originalNumToEvaluate = i;

            if (originalNumToEvaluate > 10) {
                while (numToEvaluate != 0) {
                    remainder = numToEvaluate % 10;
                    reversedNum = reversedNum * 10 + remainder;
                    numToEvaluate /= 10;
                }

                if (originalNumToEvaluate == reversedNum) {

                    palindromeNumbers.add(originalNumToEvaluate);
                }
            }

        }
        return palindromeNumbers;

    }

}
