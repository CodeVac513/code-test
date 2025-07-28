import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int start = 1;
        while(start <= N) {
            bw.write(start+"\n");
            start++;
            bw.flush();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}