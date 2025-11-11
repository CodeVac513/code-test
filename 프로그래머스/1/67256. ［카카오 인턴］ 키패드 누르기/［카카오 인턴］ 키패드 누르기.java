import java.util.*;

class Solution {
    int[] numbers;
    int[][] numbersPosition;
    String hand;
    public String solution(int[] numbers, String hand) {

        this.numbers = numbers;
        this.hand = hand;
        this.numbersPosition = new int[10][2];
        numbersPosition[0][0] = 3;
        numbersPosition[0][1] = 1;
        for(int i = 1; i < 10 ; i++) {
            numbersPosition[i][0] = (i - 1) / 3;
            numbersPosition[i][1] = (i - 1) % 3;
        }
        
        return solve();
    }
    
    String solve() {
        StringBuilder sb = new StringBuilder();
        int[] currentLeft = new int[] {3, 0};
        int[] currentRight = new int[] {3, 2};
        
        for(int targetNum : numbers) {
            int[] targetPos = numbersPosition[targetNum];
            if(targetNum == 1 || targetNum == 4 || targetNum == 7) {
                currentLeft[0] = targetPos[0];
                currentLeft[1] = targetPos[1];
                sb.append("L");
            } else if(targetNum == 3 || targetNum == 6 || targetNum == 9) {
                currentRight[0] = targetPos[0];
                currentRight[1] = targetPos[1];
                sb.append("R");
            } else {
                int leftHandDist = Math.abs(currentLeft[0] - targetPos[0]) 
                + Math.abs(currentLeft[1] - targetPos[1]);
            
                int rightHandDist = Math.abs(currentRight[0] - targetPos[0]) 
                    + Math.abs(currentRight[1] - targetPos[1]);
                if(leftHandDist > rightHandDist) {
                    currentRight[0] = targetPos[0];
                    currentRight[1] = targetPos[1];
                    sb.append("R");
                } else if(rightHandDist > leftHandDist) {
                    currentLeft[0] = targetPos[0];
                    currentLeft[1] = targetPos[1];
                    sb.append("L");
                } else {
                    if(hand.equals("right")) {
                        currentRight[0] = targetPos[0];
                        currentRight[1] = targetPos[1];
                        sb.append("R");
                    } else {
                        currentLeft[0] = targetPos[0];
                        currentLeft[1] = targetPos[1];
                        sb.append("L");
                    }
                }
            }
        }
        return sb.toString();
    }
}

// 왼손은 *, 오른손은 #에서 시작
// 키패드 전체는 [4][3]의 배열, *은 [4][0], #은 [4][2]
// 0열은 왼손, 2열은 오른손, 1열은 둘 중 더 가까운 손 / 거리가 같다면 주로 사용하는 손을 사용함.