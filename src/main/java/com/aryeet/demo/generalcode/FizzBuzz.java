package com.aryeet.demo.generalcode;

import java.util.LinkedList;
import java.util.List;

public class FizzBuzz {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String delimiter = ",";
        List<String> userInputs = new LinkedList<>();

        for(int inputVal=1 ; inputVal<= 100 ; inputVal++){
            userInputs.add(convertValueToString(inputVal));

        }
        String fuzzBuzzString = String.join(delimiter, userInputs);
        System.out.println(fuzzBuzzString);

    }

    private static String convertValueToString(int inputVal) {
        String convertedVal;
        if(inputVal % 3 == 0 && inputVal % 5 == 0){
            return convertedVal = "FizzBuzz";
        }
        if(inputVal % 3 == 0){
            return convertedVal = "Fizz";
        }
        if(inputVal % 5 == 0){
            return convertedVal = "Buzz";
        }
        return String.valueOf(inputVal);
    }

}
