import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] dx = {1, -1, 0, 0}, dy = {0 ,0, 1, -1};
    static int[][] dp;
    static char[][] chars;
    static boolean[][] visit;
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];
        visit = new boolean[N][M];
        chars = new char[N][M];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                chars[i][j] = s.charAt(j);
            }
        }

        visit[0][0] = true;
        int v = dfs(0, 0);
        System.out.println(flag ? -1 : v);
    }

    public static int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;
        int temp = 0;
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k] * (chars[x][y] - '0');
            int ny = y + dy[k] * (chars[x][y] - '0');
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || chars[nx][ny] == 'H') continue;
            if(visit[nx][ny]) {
                flag = true;
                continue;
            }

            visit[nx][ny] = true;
            temp = Math.max(temp, dfs(nx, ny));
            visit[nx][ny] = false;
        }
        return dp[x][y] += temp;
    }
}