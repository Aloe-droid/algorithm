import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] time, dp;
    static List<List<Integer>> list;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        time = new int[N + 1];
        list = new ArrayList<>();
        
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            dp[i] = -1;
        }

        for(int i = 1 ; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            
            int J = Integer.parseInt(st.nextToken());
            for(int j = 0; j < J; j++) {
                int in = Integer.parseInt(st.nextToken());
                list.get(in).add(i);
            }
        }

        int max = 0;
        for(int i = 1; i <= N; i++) max = Math.max(max, dfs(i));
        System.out.println(max);
    }

    public static int dfs(int x) {
        if(dp[x] != -1) return dp[x];

        dp[x] = time[x];
        int max = 0;
        for(int nx : list.get(x)) {
            max = Math.max(max, dfs(nx));
        }
        return dp[x] += max;
    }
}
