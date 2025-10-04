import java.util.*;
import java.lang.*;


class Solution {
    public int solution(String str1, String str2) {
        return solve(str1, str2);
    }
    
    public int solve(String str1, String str2) {
        String[] a = stringToArray(str1.toUpperCase());
        String[] b = stringToArray(str2.toUpperCase());
        
        
        String[] intersection = makeIntersection(a, b);
        String[] union = makeUnion(a, b);
        
       
        return makeAnswer(intersection, union);
    }
    
    public int makeAnswer(String[] intersection, String[] union) {
        // double로 계산한 뒤에 65536을 곱하고 integer로 형변환해야 함.
        if(union.length == 0) return 65536;
        
        double result = ((double)intersection.length / (double)union.length);
        return (int) (result * 65536);
    }
    
    public String[] makeUnion(String[] a, String[] b) {
Set<String> tempSet = new HashSet();
        Map<String, Integer> tempMapForA = new HashMap();
        Map<String, Integer> tempMapForB = new HashMap();
        ArrayList<String> tempArrayList = new ArrayList();
        
        for(int i = 0 ; i < a.length ; i++) {
            tempSet.add(a[i]);
            tempMapForA.put(a[i], tempMapForA.getOrDefault(a[i], 0) + 1);
        }
        
        for(int i = 0 ; i < b.length ; i++) {
            tempSet.add(b[i]);
            tempMapForB.put(b[i], tempMapForB.getOrDefault(b[i], 0) + 1);
        }
        
        for (String s : tempSet) {
            int iter = Math.max(tempMapForB.getOrDefault(s, 0), tempMapForA.getOrDefault(s, 0));
            for(int i = 0 ; i < iter ; i++) {
                tempArrayList.add(s);
            }
        }
        
        String[] result = new String[tempArrayList.size()];
        for(int i = 0 ; i < tempArrayList.size() ; i++) {
            result[i] = tempArrayList.get(i);
        }
        
        Arrays.sort(result);
        return result;
    }
    
    public String[] makeIntersection(String[] a, String[] b) {
        // Set - 모든 문자열을 저장
        // Map - 저장된 문자열의 카운트를 셈 - ForA와 ForB 2가지가 존재해야 함.
        
        Set<String> tempSet = new HashSet();
        Map<String, Integer> tempMapForA = new HashMap();
        Map<String, Integer> tempMapForB = new HashMap();
        ArrayList<String> tempArrayList = new ArrayList();
        
        for(int i = 0 ; i < a.length ; i++) {
            tempSet.add(a[i]);
            tempMapForA.put(a[i], tempMapForA.getOrDefault(a[i], 0) + 1);
        }
        
        for(int i = 0 ; i < b.length ; i++) {
            tempSet.add(b[i]);
            tempMapForB.put(b[i], tempMapForB.getOrDefault(b[i], 0) + 1);
        }
        
        for (String s : tempSet) {
            int iter = Math.min(tempMapForB.getOrDefault(s, 0), tempMapForA.getOrDefault(s, 0));
            for(int i = 0 ; i < iter ; i++) {
                tempArrayList.add(s);
            }
        }
        
        String[] result = new String[tempArrayList.size()];
        for(int i = 0 ; i < tempArrayList.size() ; i++) {
            result[i] = tempArrayList.get(i);
        }
        
        Arrays.sort(result);
        return result;
    }
    
    public String[] stringToArray(String s) {        
        ArrayList<String> temp = new ArrayList();
        
        for(int i = 0 ; i < s.length() -1 ; i++) {
            String token = s.substring(i, i + 2);
            if(existSpecialSymbols(token)) {
                continue;
            }
            temp.add(token);
        }
        
        String[] result = new String[temp.size()];
        for(int i = 0 ; i < temp.size() ; i++) {
            result[i] = temp.get(i);
        }
        
        return result;
    }
    
    public boolean existSpecialSymbols(String token) {
        for(int i = 0 ; i < token.length() ; i++) {
            char currentChar = token.charAt(i);
            switch (currentChar) {
                case 'A':
                    break;
                case 'B':
                    break;
                case 'C':
                    break;
                case 'D':
                    break;
                case 'E':
                    break;
                case 'F':
                    break;
                case 'G':
                    break;
                case 'H':
                    break;
                case 'I':
                    break;
                case 'J':
                    break;
                case 'K':
                    break;
                case 'L':
                    break;
                case 'M':
                    break;
                case 'N':
                    break;
                case 'O':
                    break;
                case 'P':
                    break;
                case 'Q':
                    break;
                case 'R':
                    break;
                case 'S':
                    break;
                case 'T':
                    break;
                case 'U':
                    break;
                case 'V':
                    break;
                case 'W':
                    break;
                case 'X':
                    break;
                case 'Y':
                    break;
                case 'Z':
                    break;
                default:
                    return true;
            }
        }
        return false;
    }
}