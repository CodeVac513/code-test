import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 1564번
    // 수학 문제
    // 나머지 연산(%)과 나눗셈(/)으로 자리수를 구하는 문제인듯 하다.
    // 오답 노트)
    // 자리수가 너무 길어져서 오버플로우가 일어나는 듯하다.
    // BigInteger를 활용해서 정확한 계산을 할 수 있도록 만들자.
    // 오답 노트2)
    // 메모리 초과 발생
    // 끝자리 0을 제거하면서, 앞자리도 일부분 잘라내자. 어차피 뒤의 5자리만 필요하기 때문에 앞의 숫자가 얼마가 되던 상관없음.
    // 오답 노트3)
    // 모듈로 크기를 12자리(1e12)로 설정하고, 조건 없이 매번 나머지 연산 적용

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        Long num = 1L;
        Long modV = (long)1e12;  // 12자리로 제한

        for (int i = 1; i <= N; i++) {
            num = num * i;
            while (num % 10 == 0) {
                num /= 10;
            }

            num %= modV;
        }

        String result = String.valueOf(num);
        bw.write(result.substring(result.length() - 5));
        bw.newLine();

        br.close();
        bw.flush();
        bw.close();
    }
}