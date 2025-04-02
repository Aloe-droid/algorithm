import java.io.*;
import java.util.*;

class Main {
    static int N, T = 0;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] ints, dp;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(++T >= 0){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            ints = new int[N][N];
            dp = new int[N][N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    ints[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            sb.append("Problem ").append(T).append(": ").append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs() {
        pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        pq.add(new int[] {0, 0, ints[0][0]});

        while(!pq.isEmpty()) {
            int x = pq.peek()[0];
            int y = pq.peek()[1];
            int w = pq.poll()[2];
            if(dp[x][y] <= w) continue;
            dp[x][y] = w;

            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(dp[nx][ny] <= dp[x][y] + ints[nx][ny]) continue;
                pq.add(new int[] {nx, ny, dp[x][y] + ints[nx][ny]});
            }
        }

        return dp[N - 1][N - 1];
    }
}
