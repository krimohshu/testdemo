package com.aryeet.demo.generalcode;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Fibonacci {

    public static void main(String[] args) {
        List<BigInteger> userInputs = new LinkedList<>();
        String delimiter = ",";
        int counter = 1;
        int counterToReachinFab = 35;
        BigInteger beginningNumber = BigInteger.valueOf(1);
        BigInteger secondNumberOfSeries = BigInteger.valueOf(1);


        while (counter <= counterToReachinFab) {

            userInputs.add(beginningNumber);
            BigInteger nextTerm = beginningNumber.add(secondNumberOfSeries);
            beginningNumber = secondNumberOfSeries;
            secondNumberOfSeries = nextTerm;
            counter++;
        }
        StringJoiner joiner = new StringJoiner(",");
        userInputs.forEach(item -> joiner.add(item.toString()));
        System.out.println(joiner.toString());
        System.out.println("****** THE REQUIRED VALUE OF FAB SERIES = " + userInputs.get(counterToReachinFab-1));


    }
}
