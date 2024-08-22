import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] ints = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            ints[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(ints[i] >= ints[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) max = Math.max(max, dp[i]);
        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }
}