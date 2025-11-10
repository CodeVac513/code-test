import java.util.*;

class Solution {
    int[][] board;
    int[] moves;
    int n;
    public int solution(int[][] board, int[] moves) {
        
        this.board = board;
        this.moves = moves;
        this.n = board.length;

        return solve();
    }
    
    public int solve() {
        int answer = 0;
        ArrayList<Integer> bucket = new ArrayList();
        int bucketIndex = 0;
        for(int index : moves) {
            for(int i = 0 ; i < n ; i++) {
                int currentDoll = board[i][index - 1];

                if(currentDoll == 0) continue;
                
                bucket.add(bucketIndex, currentDoll);
                if(bucketIndex > 0) {
                    int prevDoll = bucket.get(bucketIndex - 1);
                    if(prevDoll == currentDoll) {
                        bucketIndex--;
                        answer += 2;
                    } else {
                        bucketIndex++;
                    }
                } else {
                    bucketIndex++;
                }
                board[i][index - 1] = 0;
                break;
            }
        }
        
        return answer;
    }
}

// 1 * 1의 칸으로 이루어진 N * N의 격자
// 바구니는 linkedlist 형태이며, 같은 인형 2개가 연속되면 그 인형은 사라짐.
// 그리고 바구니의 크기는 모든 인형이 들어갈만큼 충분히 큼. (인형 개수만큼 계속 늘어날 수 있음.)
// 문제에 나타난 규칙대로 구현하면 되는 문제인듯 함.