import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints, dp;
    static int[] dx = {1, -1, 0, 0,}, dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        System.out.println(max);
    }

    public static int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;
        int temp = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if(ints[nx][ny] <= ints[x][y]) continue;
            temp = Math.max(temp, dfs(nx, ny));
        }

        return dp[x][y] += temp;
    }
}