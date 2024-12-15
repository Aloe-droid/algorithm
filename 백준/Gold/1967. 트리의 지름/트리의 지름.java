import java.io.*;
import java.util.*;

class Main {
    static List<List<Road>> list;
    static int[] dp;
    static int max = 1_000_000_000;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        dp = new int[N + 1];
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            list.get(x).add(new Road(y, z));
            list.get(y).add(new Road(x, z));
        }

        Arrays.fill(dp, max);
        dp[1] = 0;
        dfs(1, 0);

        int dst = 0;
        for(int i = 1; i <= N; i++) dst = Math.max(dst, dp[i]);

        List<Integer> temp = new ArrayList<>();
        for(int i = 1; i <= N; i++) if(dp[i] == dst) temp.add(i);

        int ans = 0;
        for(int idx : temp) {
            Arrays.fill(dp, max);
            dp[idx] = 0;
            dfs(idx, 0);

            for(int i = 1; i <= N; i++) ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }

    public static void dfs(int node, int weight){
        if(dp[node] < weight) return;

        for(Road next : list.get(node)){
            if(dp[next.node] <= dp[node] + next.weight) continue;
            dp[next.node] = dp[node] + next.weight;
            dfs(next.node, dp[next.node]);
        }
    }
}

class Road {
    int node, weight;
    public Road(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}