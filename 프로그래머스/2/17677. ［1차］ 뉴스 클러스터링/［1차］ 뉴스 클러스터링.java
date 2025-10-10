import java.util.*;

class Solution {
    // <입력>
    //     문자열 2개의 입력, 2이상 1000이하의 길이
    // 1. 입력으로 들어온 문자열은 두 글자씩 끊어서 다중 집합(중복 허용)을 생성한다.
    //     - 공백, 숫자, 특수 문자 등이 들어 있으면 그 글자 쌍은 버린다.
    //     - 대문자/소문자의 차이는 무시한다. (toUpperCase()로 처리.)
    // 2. 두 집합을 연산하면서 자카드 유사도를 생성한다.
    //     - 교집합의 개수 / 합집합의 개수 
    //     - 공집합의 경우, 1로 정의
    
    
    public int solution(String str1, String str2) {
        int answer = 0;
        String[] str1Tokens = stringToArray(str1);
        String[] str2Tokens = stringToArray(str2);
        String[] intersection = makeIntersection(str1Tokens, str2Tokens);
        String[] union = makeUnion(str1Tokens, str2Tokens);
        answer = calculateJaccad(intersection, union);
        return answer;
    }
    
    public int calculateJaccad(String[] intersection, String[] union) {
        double result = 0;
        if(union.length == 0) {
            result = 1;
        } else {
            result = ((double) intersection.length) / ((double) union.length);
        }
        
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
        s = s.toUpperCase();
        ArrayList<String> tokens = new ArrayList();
        
        for(int i = 0 ; i < s.length() - 1 ; i++) {
            String token = s.substring(i, i + 2);
            if(isValid(token)) {
                tokens.add(token);
            }
        }
        
        return tokens.toArray(new String[0]);
    }
    
    public boolean isValid(String token) {
        for(int i = 0 ; i < token.length() ; i++) {
            char c = token.charAt(i);
            if(c < 'A' || c > 'Z') {
                return false;
            }
        }
        return true;
    }
}