import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw.write("\\    /\\\n");
        bw.write(" )  ( ')\n");
        bw.write("(  /  )\n");
        bw.write(" \\(__)|\n");
        bw.flush();
        bw.close();
        br.close();
    }
}