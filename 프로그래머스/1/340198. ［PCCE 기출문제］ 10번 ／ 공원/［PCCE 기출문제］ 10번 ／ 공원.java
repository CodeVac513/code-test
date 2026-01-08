import java.util.*;

class Solution {
    
    Integer[] mats;
    String[][] park;
    int parkRow;
    int parkCol;
    
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        this.park = park;
        this.mats = new Integer[mats.length];
        parkRow = park.length;
        parkCol = park[0].length;
        
        for(int i = 0 ; i < mats.length ; i++) {
            this.mats[i] = mats[i];
        }
        
        Arrays.sort(this.mats, (Integer a, Integer b) -> b - a);
        
        for(int mat : this.mats) {
            if(findPlace(mat)) {
                answer = mat;
                break;
            }
        }
        
        return answer;
    }
    
    public boolean findPlace(int matSize) {        
        for(int i = 0 ; i < parkRow ; i++) {
            for(int j = 0 ; j < parkCol ; j++) {
                if(isPossible(i, j, matSize)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isPossible(int row, int col, int matSize) {
        for(int i = 0 ; i < matSize ; i++) {
            for(int j = 0 ; j < matSize ; j++) {
                if(row + i >= parkRow || col + j >= parkCol) return false;
                if(!park[row+i][col+j].equals("-1")) return false;
            }
        }
        return true;
    }
}

// 탐색을 통해서 겹치는 부분이 있는지 확인하면 되겠는데?