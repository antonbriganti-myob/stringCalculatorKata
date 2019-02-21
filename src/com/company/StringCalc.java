package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalc {

    public int add(String input1, String input2){
        return Integer.valueOf(input1) + Integer.valueOf(input2);
    }

    public int add(String input) throws Exception{
        String[] splitStrings;
        int total = 0;

        String regexPattern = ",|\\n|//|\\[|\\]";
        ArrayList<String> delimiters = findnewDelimiters(input);

        for(String delimiter:delimiters){
            regexPattern += "|" + delimiter;
        }

        splitStrings = input.split(regexPattern);

        for(String str:splitStrings){
            if (!str.isEmpty()){
                int value = convertStringToInteger(str);
                total += value;

            }
        }

        return total;
    }

    private int convertStringToInteger(String str) throws Exception {
        int value = Integer.valueOf(str);
        if(value < 0){
            throw new Exception("Negatives not allowed");
        }
        if (value >= 1000){
            return 0;
        }
        return value;
    }


    private ArrayList<String> findnewDelimiters(String input){
        ArrayList<String> delimiters = new ArrayList<>();

        if(input.contains("//"))
        {
            if(!input.contains("[")){
                delimiters.add(input.split("//")[1].substring(0, 1));
            }
            else{
                Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                Matcher matcher = pattern.matcher(input);

                while(matcher.find()) {
                    delimiters.add(matcher.group(1));
                }
            }

        }

        return delimiters;
    }


}
