import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1622번
    // 구현
    // 순열과 교집합
    // 오답 노트) 빈 줄이 입력될 수도 있음.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLineA = "";
        String inputLineB = "";
        ArrayList<String> answer = new ArrayList<>();
        while ((inputLineA = br.readLine()) != null && (inputLineB = br.readLine()) != null) {
            answer.add(solve(inputLineA, inputLineB));
        }

        for (String s : answer) {
            bw.write(s);
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static String solve(String inputA, String inputB) {
        String[] a = stringToArray(inputA);
        String[] b = stringToArray(inputB);

        String[] intersection = makeIntersection(a, b);

        return makeAnswer(intersection);
    }

    public static String makeAnswer(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String[] stringToArray(String s) {
        String[] result = new String[s.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.substring(i, i + 1);
        }
        return result;
    }

    public static String[] makeIntersection(String[] a, String[] b) {
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> mapForA = new HashMap<>();
        HashMap<String, Integer> mapForB = new HashMap<>();


        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
            mapForA.put(a[i], mapForA.getOrDefault(a[i], 0) + 1);
        }

        for (int i = 0; i < b.length; i++) {
            set.add(b[i]);
            mapForB.put(b[i], mapForB.getOrDefault(b[i], 0) + 1);
        }

        ArrayList<String> intersection = new ArrayList<>();
        for (String s : set) {
            int iter = Math.min(mapForA.getOrDefault(s, 0), mapForB.getOrDefault(s, 0));
            for (int i = 0; i < iter; i++) {
                intersection.add(s);
            }
        }
        intersection.sort(String::compareTo);
        return intersection.toArray(new String[intersection.size()]);
    }

}
