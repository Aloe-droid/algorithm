import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[91];
        
        dp[0] = 0L;
        dp[1] = 1L;
        
        for(int i = 2; i < dp.length; i++) dp[i] = dp[i - 1] + dp[i - 2];
        bw.write(dp[n] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}