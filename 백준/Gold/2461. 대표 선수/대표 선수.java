import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] ints = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) ints[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) Arrays.sort(ints[i]);
        PriorityQueue<Student> pq = new PriorityQueue<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            pq.add(new Student(i, 0, ints[i][0]));
            min = Math.min(min, ints[i][0]);
            max = Math.max(max, ints[i][0]);
        }

        int diff = Integer.MAX_VALUE;
        while (pq.size() == N) {
            Student minStudent = pq.poll();
            min = minStudent.value;
            diff = Math.min(diff, Math.abs(max - min));

            int ny = minStudent.y + 1;
            if (ny < M) {
                pq.add(new Student(minStudent.x, ny, ints[minStudent.x][ny]));
                max = Math.max(max, ints[minStudent.x][ny]);
            }
        }
        System.out.println(diff);
    }
}

class Student implements Comparable<Student> {
    int x;
    int y;
    int value;

    Student(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public int compareTo(Student o) {
        return value - o.value;
    }
}