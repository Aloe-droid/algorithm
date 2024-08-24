import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 2];
        int[][] ints = new int[n + 2][2];

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ints[i][0] = Integer.parseInt(st.nextToken());
            ints[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < dp.length; i++){
            dp[i] = Math.max(dp[i], dp[i - 1]);
            int end = i + ints[i][0];

            if(end >= dp.length) continue;
            dp[end] = Math.max(dp[end], dp[i] + ints[i][1]);
        }

        System.out.println(dp[dp.length - 1]);
    }
}