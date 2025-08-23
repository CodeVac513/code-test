import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    static int[] ricecakeByDay;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());

        for(int i = N ; i >= 1 ; i--) {
            bw.write(String.valueOf(i)+"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

}