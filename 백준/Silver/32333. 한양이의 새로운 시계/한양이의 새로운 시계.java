import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 32333번
    // 구현, 시뮬레이션
    // 15개의 동물로 24시간을 표현 -> 1마리당 96분을 담당하게 됨.
    // 시작 시간과 소요 시간을 입력받는다. 종료 시간과 동물마다 담당하는 시간을 지났으면 그 동물들을 출력한다.
    // 예를 들어, 쥐와 소, 호랑이 시간을 1분이라도 사용했다고 가정하면 쥐 소 호랑이라고 출력을 해야 하는 것이다.
    // 시와 분을 나눠서 생각하면 복잡하기에 쥐는 0, 소는 96, 호랑이는 192... 이런 식으로 연산하는 게 좋을 것 같다.
    // 그리고 가장 빠른 시간으로 정렬을 해야 한다.
    // 그리고 시간의 흐름에 따라 생각해야 한다. 문제 2번 예시 입력 같은 경우, 같은 시간이 2개이기에 시작 시간을 잘 고려해야 함을 생각하게 만들어준다.
    // 오답 노트)
    // 중간에 continue로 24시간이 넘는 작업을 판별하니 StringBuilder에 쌓여서 출력초과가 발생했다. 이 부분을 break로 수정하자.

    static Queue<String> animals;
    static String[] animalList = {"rat", "cow", "tiger", "rabbit", "dragon", "snake", "horse", "sheep", "monkey",
            "chicken", "dog", "pig", "lion", "giraffe", "cat"};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Job> jobList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int requiredTime = Integer.parseInt(st.nextToken());

            int startTime = hoursToMinutes(H, M);
            jobList.add(new Job(startTime, requiredTime));
        }

        Collections.sort(jobList, (Job job1, Job job2) -> {
            if (job1.startTime == job2.startTime) {
                return job1.requiredTime - job2.requiredTime;
            }
            return job1.startTime - job2.startTime;
        });

        int incompletedJobCount = 0;
        int currentTime = 0;
        for (int i = 0; i < N; i++) {
            Job job = jobList.get(i);
            animals = new LinkedList<>();

            if (job.startTime >= currentTime) {
                currentTime = job.startTime;
            }
            int endTime = currentTime + job.requiredTime;

            checkAnimals(currentTime, endTime);

            StringBuilder sb = new StringBuilder();

            while (!animals.isEmpty()) {
                sb.append(animals.poll()).append(" ");
            }

            int startHour = currentTime / 60;
            int startMinute = currentTime % 60;
            int endHour = endTime / 60;
            int endMinute = endTime % 60;

            if (endHour >= 24) {
                incompletedJobCount += N - i;
                break;
            }

            bw.write(sb.toString() + "\n");
            bw.write(String.format("%02d:%02d %02d:%02d", startHour, startMinute, endHour, endMinute) + "\n");
            currentTime = endTime;
        }

        if (incompletedJobCount > 0) {
            bw.write(String.valueOf(incompletedJobCount) + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void checkAnimals(int startTime, int endTime) {
        for (int i = 0; i < 15; i++) {
            int animalStart = i * 96;
            int animalEnd = (i + 1) * 96;
            if (startTime < animalEnd && endTime >= animalStart) {
                animals.add(animalList[i]);
            }
        }

    }

    public static int hoursToMinutes(int hours, int minutes) {
        return hours * 60 + minutes;
    }

    static public class Job {

        int startTime;
        int requiredTime;

        Job(int startTime, int requiredTime) {
            this.startTime = startTime;
            this.requiredTime = requiredTime;
        }
    }
}
