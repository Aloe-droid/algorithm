import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints, dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String temp = null;
        while((temp = br.readLine()) != null) {
            N = Integer.parseInt(temp.trim());
            ints = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());
            sb.append(find()).append("\n");
        }
        System.out.println(sb);
    }

    public static int find() {
        dp = new int[N + 1];
        int pos = 0;
        
        for(int k : ints) {
            if(dp[pos] < k) {
                dp[++pos] = k;
                continue;
            }
            
            int left = 0, right = pos;
            while(left <= right) {
                int mid = (left + right) / 2;
                
                if(dp[mid] < k) left = mid + 1;
                else right = mid - 1;
            }
            dp[left] = k;
        }

        return pos;
    }
}