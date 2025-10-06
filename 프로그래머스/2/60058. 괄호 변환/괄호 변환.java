import java.util.*;
import java.lang.*;

class Solution {
    public String solution(String p) {
        String answer = recursion(p);
        return answer;
    }
    
    public String recursion(String w) {
        if(w.equals("")) {
            return "";
        }
        
        String[] splitedString = splitInputString(w);
        String u = splitedString[0];
        String v = splitedString[1];
        
        if(isCorrected(u)) {
            String result = recursion(v);
            return u + result;
        } else {
            String result = "(";
            result += recursion(v);
            result += ")";
            u = u.substring(1, u.length() - 1);
            result += reverse(u);
            return result;
        }
    }
    
    public String[] splitInputString(String w) {
        String u = "";
        String v = "";
        int index = 1;
        while(index <= w.length()) {
            u = w.substring(0, index);
            v = w.substring(index, w.length());
            if(isBalanced(u)) break;
            index++;
        }
        String[] result = {u, v};
        return result;
    }
    
    public boolean isBalanced(String u) {
        int countLeft = 0;
        int countRight = 0;
        
        for(int i = 0 ; i < u.length() ; i++) {
            if(u.substring(i, i + 1).equals("(")) {
                countLeft++;
            } else {
                countRight++;
            }
        }
        
        if(countLeft == countRight) return true;
        
        return false;
    }
    
    public boolean isCorrected(String u) {
        int count = 0;
        for(int i = 0; i < u.length(); i++) {
            if(u.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if(count < 0) return false;
            }
        return count == 0;
    }
    
    public String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++) {
            if(s.substring(i, i + 1).equals("(")) {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }
        return sb.toString();
    }
}