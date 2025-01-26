import java.io.*;
import java.util.*;

class Main {
    static int N, K, W;
    static int[] times, dp;
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            times = new int[N + 1];
            dp = new int[N + 1];
            list = new ArrayList<>();
            for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) times[i] = Integer.parseInt(st.nextToken());
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.get(y).add(x);
            }
            W = Integer.parseInt(br.readLine());

            Arrays.fill(dp, -1);
            for(int i = 1; i <= N; i++) if(list.get(i).size() == 0) dp[i] = times[i];

            sb.append(dfs(W)).append("\n");
        }
        System.out.println(sb);
    }

    public static int dfs(int id) {
        if(dp[id] != -1) return dp[id];

        dp[id] = 0;
        for(int next : list.get(id)) dp[id] = Math.max(dp[id], dfs(next) + times[id]);
        return dp[id];
    }
}

