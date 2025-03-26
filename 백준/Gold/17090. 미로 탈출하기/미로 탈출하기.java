import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][] ints, dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                dp[i][j] = -1;
                
                char c = s.charAt(j);
                if(c == 'U') ints[i][j] = 0;
                else if(c == 'D') ints[i][j] = 1;
                else if(c == 'L') ints[i][j] = 2;
                else ints[i][j] = 3;
            }
        }

        int s = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                s += dfs(i, j);
            }
        }
        System.out.println(s);
    }

    public static int dfs(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        int dir = ints[x][y];
        dp[x][y] = 0;
        dp[x][y] += dfs(x + dx[dir], y + dy[dir]);
        return dp[x][y];
    }
}
