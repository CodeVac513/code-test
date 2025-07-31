import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        String[] nums = br.readLine().split(" ");
        Integer target = Integer.parseInt(br.readLine());
        Integer ans = 0;
        for(int i = 0 ; i < N ; i++) {
            if(Integer.parseInt(nums[i]) == target) ans++;
        }
        bw.write(ans+"\n");
        br.close();
        bw.flush();
        bw.close();
    }
}