import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        Integer num = 2;
        for(int i = 0 ; i < N ; i++) {
            num = num * 2 - 1;
        }
        
        bw.write((num * num) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}