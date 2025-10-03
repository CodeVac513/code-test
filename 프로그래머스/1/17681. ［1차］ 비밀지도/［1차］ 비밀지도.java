import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        return solve(arr1, arr2, n);
    }
    
    public Integer[] integerToBinary(int num, int n) {
        String binary = Integer.toBinaryString(num);
        while(binary.length() < n) {
            binary = "0" + binary;
        }
        
        String[] temp = binary.split("");
        Integer[] result = new Integer[temp.length];
        for(int i = 0 ; i < temp.length ; i++) {
            result[i] = Integer.parseInt(temp[i]);
        }

        return result;
    }
    
    public Integer[][] integerArrayToMap(int[] arr, int n) {
        Integer[][] map = new Integer[n][n];
        for(int i = 0 ; i < n ; i++) {
            map[i] = integerToBinary(arr[i], n);
        }
        
        return map;
    }
    
    public String[] solve(int[] arr1, int[] arr2, int n) {
        Integer[][] mapA = integerArrayToMap(arr1, n);
        Integer[][] mapB = integerArrayToMap(arr2, n);
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                mapA[i][j] += mapB[i][j];
            }
        }

        return convertMapToAnswer(mapA, n);
    }
    
    public String[] convertMapToAnswer(Integer[][] map, int n) {
        String[] answer = new String[n];
        for(int i = 0 ; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int num : map[i]) {
                if(num > 0) sb.append("#");
                else sb.append(" ");
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}