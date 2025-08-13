import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 11944번
    // 구현 문제
    // N을 M자리수만큼 출력한다. -> String으로 만들고 subString(0, M)을 하면되지 않을까?
    // => StringIndexOutOfBounds, String이 너무 길어져서 에러가 발생함. 4*2016, 즉 8064의 길이를 가지는 경우처럼 너무 길면 예외 발생
    // M/(N의 길이, N의 자리수)만큼 for문을 반복
    // => 5/2를 하면 2가 된다. 그러면 자리수가 4만큼만 출력되고 5번째는 없어짐. 이런 경우를 대비해서 로직을 수정
    // N번 반복을 하되 길이가 M을 넘으면 중단한다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < Integer.parseInt(nums[0]); i++){
            if(sb.length() + nums[0].length() > Integer.parseInt(nums[1])){
                int remain = Integer.parseInt(nums[1]) - sb.length();
                sb.append(nums[0].substring(0, remain));
                break;
            }
            sb.append(nums[0]);
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }


}