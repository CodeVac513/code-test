import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            Integer input = Integer.parseInt(br.readLine());
            if(input == 0) break;
            Integer ans = 0;
            for(int i = 1 ; i <= input ; i++) {
                ans += i * i;
            }
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}