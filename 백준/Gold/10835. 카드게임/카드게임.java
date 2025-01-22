import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] left, right;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        left = new int[N];
        right = new int[N];
        dp = new int[N][N];

        String[] s1 = br.readLine().split(" ");
        String[] s2 = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            left[i] = Integer.parseInt(s1[i]);
            right[i] = Integer.parseInt(s2[i]);
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int l, int r) {
        if(l >= N || r >= N) return 0;
        if(dp[l][r] != -1) return dp[l][r];

        dp[l][r] = 0;

        // 왼쪽만 버리는 경우
        int v1 = dfs(l + 1, r);
        // 왼쪽 오른쪽 둘다 버리는 경우
        int v2 = dfs(l + 1, r + 1);
        // 오른쪽만 버리는 경우
        int v3 = (left[l] > right[r]) ? right[r] + dfs(l, r + 1) : 0;

        return dp[l][r] = Math.max(Math.max(v1, v2), v3);
    }
}