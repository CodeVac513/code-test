import java.util.*;
import java.lang.Math;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        Long N = Long.parseLong(temp[0]);
        Long M = Long.parseLong(temp[1]);
        bw.write(Math.abs(N - M)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}