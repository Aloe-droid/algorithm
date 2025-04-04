import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max = 100_000_000;
    static int[][] ints, dp;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ints = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                ints[i][j] = s.charAt(j) - '0';
                dp[i][j] = max;
            }
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        bfs();
        System.out.println(dp[N - 1][M - 1]);
    }

    public static void bfs() {
        dp[0][0] = 0;
        pq.add(new int[] { 0, 0, 0});

        while(!pq.isEmpty()) {
            int x = pq.peek()[0];
            int y = pq.peek()[1];
            int w = pq.poll()[2];
            if(dp[x][y] < w) continue;

            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                int cost = dp[x][y] + ints[nx][ny];
                if(dp[nx][ny] <= cost) continue;
                dp[nx][ny] = cost;
                pq.add(new int[] { nx, ny, dp[nx][ny] });
            }
        }
    }
}
