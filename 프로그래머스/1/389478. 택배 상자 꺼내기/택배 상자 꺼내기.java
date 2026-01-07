class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int floors = num / w; 
        int totalFloors = n / w;
        if(n % w > 0) {
            totalFloors++;
        }
        if(num % w > 0) {
            floors++;
        }
        int x = 0;
        if(floors % 2 == 0) {
            for(int i = 0 ; i < w ; i++) {
                int temp = floors * w - i;
                if(temp == num) {
                    x = i;
                    break;
                }
            }
        } else {
            for(int i = 0 ; i < w ; i++) {
                int temp = (floors - 1) * w + i + 1;
                if(temp == num) {
                    x = i;
                    break;
                }
            }
        }
        
        if(totalFloors % 2 == 0) {
            int temp = totalFloors * w - x;
            if(temp > n) {
                answer = totalFloors - floors;
            } else {
                answer = totalFloors - floors + 1;
            }
        } else {
            int temp = (totalFloors - 1) * w + x + 1;
            if(temp > n) {
                answer = totalFloors - floors;
            } else {
                answer = totalFloors - floors + 1;
            }
        }        
        return answer;
    }
}

// 홀수층: (층수 n - 1) * w +1, ...
// 짝수층: (층수 n) * w, -1, ...