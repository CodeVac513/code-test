class Solution {
    // 브루트포스로 했을 때, 시간 초과가 발생하진 않을까?
    // 일단 브루트포스로 풀어보고 시간 계산을 해보자.
    // 후에 차분 배열을 이용할 수 있을지 고려. 
    // 아니다. 이차원으로 차분 배열을 고려해보자.
    int[][] board;
    int[][] diffAttack;
    int[][] diffRestore;
    int N;
    int M;
    
    public int solution(int[][] board, int[][] skill) {
        
        // 보드와 보드의 크기 설정
        this.board = board;
        N = board.length;
        M = board[0].length;
        diffAttack = new int[N + 1][M + 1];
        diffRestore = new int[N + 1][M + 1];
        
        for(int[] currentSkill : skill) {
            int type = currentSkill[0];
            int startRow = currentSkill[1];
            int endRow = currentSkill[3];
            int startColumn = currentSkill[2];
            int endColumn = currentSkill[4];
            int degree = currentSkill[5];
            
            if(type == 1) {
                attack(startRow, endRow, startColumn, endColumn, degree);
            } else {
                restore(startRow, endRow, startColumn, endColumn, degree);
            }
        }
        
        calculatePrefixSum();
        
        int answer = calculateResult();
        
        return answer;
    }
    
    public void attack(int startRow, int endRow, int startColumn, int endColumn, int degree) {
        
        diffAttack[startRow][startColumn] += degree;
        
        diffAttack[startRow][endColumn + 1] -= degree;
        diffAttack[endRow + 1][startColumn] -= degree;
        
        diffAttack[endRow + 1][endColumn + 1] += degree;
    }
    
    public void restore(int startRow, int endRow, int startColumn, int endColumn, int degree) {
        
        diffRestore[startRow][startColumn] += degree;
        
        diffRestore[startRow][endColumn + 1] -= degree;
        diffRestore[endRow + 1][startColumn] -= degree;
        
        diffRestore[endRow + 1][endColumn + 1] += degree;
    }
    
    public void calculatePrefixSum() {
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(j > 0) {
                    // 가로로 전파
                    diffAttack[i][j] += diffAttack[i][j - 1];
                    diffRestore[i][j] += diffRestore[i][j - 1];
                }
                
                if(i > 0) {
                    // 세로로 전파
                    diffAttack[i][j] += diffAttack[i - 1][j];
                    diffRestore[i][j] += diffRestore[i - 1][j];
                }
                
                if(i > 0 && j > 0) {
                    // 가로와 세로로 전파되며 겹치는 부분 보정
                    diffAttack[i][j] -= diffAttack[i - 1][j - 1];
                    diffRestore[i][j] -= diffRestore[i - 1][j - 1];
                }
            }
        }
    }
    
    public int calculateResult() {
        int count = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                board[i][j] -= diffAttack[i][j];
                board[i][j] += diffRestore[i][j];
                if(board[i][j] <= 0) {
                    count++;
                }
            }
        }
        return N * M - count;
    }
}