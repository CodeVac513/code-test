import java.util.*;
import java.io.*;
import java.lang.*;


class Solution {
    String[] numWithAlphabets = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public int solution(String s) {
        int answer = solve(s.split(""));
        return answer;
    }
    
    public int solve(String[] stringTokenArray) {
        StringBuilder sb = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        for(String token: stringTokenArray) {
            try {
                int currentNum = Integer.parseInt(token);
                    answer.append(currentNum);
                    sb = new StringBuilder();
            } catch (NumberFormatException e) {
                sb.append(token);
                if(sb.length() > 0 && isNumber(sb.toString())) {
                    answer.append(stringToInteger(sb.toString()));
                    sb = new StringBuilder();
                }
            }
        }
        
        return Integer.parseInt(answer.toString());
    }
    
    public boolean isNumber(String s) {
        switch (s) {
            case "one": return true;
            case "two": return true;
            case "three": return true;
            case "four": return true;
            case "five": return true;
            case "six": return true;
            case "seven": return true;
            case "eight": return true;
            case "nine": return true;
            case "zero": return true;
            default: return false;
        }
    }
    
    public int stringToInteger(String numberString) {
        switch (numberString) {
            case "one": return 1;
            case "two": return 2;
            case "three": return 3;
            case "four": return 4;
            case "five": return 5;
            case "six": return 6;
            case "seven": return 7;
            case "eight": return 8;
            case "nine": return 9;
            default: return 0;
        }
    }
}