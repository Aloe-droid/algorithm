import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[][] ints;
    static Queue<int[]> q;
    static boolean[][] check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int cnt;
    static int temp;
    static int time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = 0;
        time = 0;
        ints = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                if (ints[i][j] == 1) cnt++;
            }
        }

        while (cnt != 0) bfs();
        System.out.println(time);
        System.out.println(temp);
    }

    public static void bfs() {
        q = new LinkedList<>();
        check = new boolean[N][M];
        q.add(new int[]{0, 0});
        check[0][0] = true;
        temp = 0;

        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || check[nx][ny]) continue;

                check[nx][ny] = true;
                if (ints[nx][ny] == 0) q.add(new int[]{nx, ny});

                if (ints[nx][ny] == 1) {
                    temp++;
                    ints[nx][ny] = 0;
                }
            }
        }

        cnt -= temp;
        time++;
    }
}