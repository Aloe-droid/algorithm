import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N ; i++) ints[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) if(ints[i] == 0) dfs(i);

        int max = 0;
        for(int k : dp) max = Math.max(max, k);
        System.out.println(max);
    }

    public static int dfs(int k) {
        if(dp[k] != 0) return dp[k];

        dp[k] = 1;
        int nK = nextK(ints[k]);
        for(int i = k + 1; i < N; i++) {
            if(ints[i] == nK) {
                dp[k] += dfs(i);
                break;
            }
        }
        return dp[k];
    }

    public static int nextK(int k) {
        if(k == 0) return 1;
        else if(k == 1) return 2;
        else return 0;
    }
}