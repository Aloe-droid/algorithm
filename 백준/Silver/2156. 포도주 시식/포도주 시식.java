import java.io.*;

class Main {
    static int N;
    static int[] ints, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        dp = new int[N];
        for(int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(br.readLine());
            dp[i] = -1;
        }
        System.out.println(dfs(0));
    }

    public static int dfs(int k) {
        if(k >= N) return 0;
        if(dp[k] != -1) return dp[k];

        dp[k] = 0;
        // 이번 포도주와 다음 포도주를 먹고 그 다음 포도주를 건너 뜀.
        int v1 = k + 1 < N ? ints[k] + ints[k + 1] + dfs(k + 3) : 0;
        // 이번 포도주를 먹고 다음 포도주로 건너 뜀.
        int v2 = ints[k] + dfs(k + 2);
        // 이번 포도주를 건너뜀
        int v3 = dfs(k + 1);

        return dp[k] = Math.max(Math.max(v1, v2), v3);
    }
}
