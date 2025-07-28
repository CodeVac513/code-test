import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer[] normalChess = new Integer[6];
        init(normalChess);
        
        String[] temp = br.readLine().split(" ");
        
        Integer[] whitePiece = new Integer[6];
        
        for(int i = 0 ; i < 6 ; i++) {
            whitePiece[i] = Integer.parseInt(temp[i]);
        }
        
        Integer[] answers = new Integer[6];
        for(int i = 0 ; i < 6 ; i++) {
            answers[i] = normalChess[i] - whitePiece[i];
        }
        
        for(int ans : answers) {
            bw.write(ans+" ");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void init(Integer[] chess) {
        chess[0] = 1;
        chess[1] = 1;
        chess[2] = 2;
        chess[3] = 2;
        chess[4] = 2;
        chess[5] = 8;
    }
}