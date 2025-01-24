import java.io.*;
import java.util.*;

class Main {
    static int N, max = 100_000_000;
    static int[][] ints;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        dp = new int[N][1 << N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());
                ints[i][j] = k == 0 ? max : k;
            }
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    public static int dfs(int x, int visited) {
        // 모든 도시를 방문했을 때
        if(visited == (1 << N) - 1) return ints[x][0];

        // 이미 계산된 경로인 경우
        if(dp[x][visited] != -1) return dp[x][visited];

        dp[x][visited] = max;

        for(int nx = 1; nx < N; nx++) {
            // 이미 방문한 도시면 스킵
            if((visited & (1 << nx)) != 0) continue;

            int nextVisited = visited | (1 << nx);
            int value = dfs(nx, nextVisited) + ints[x][nx];
            dp[x][visited] = Math.min(dp[x][visited], value);
        }

        return dp[x][visited];
    }
}