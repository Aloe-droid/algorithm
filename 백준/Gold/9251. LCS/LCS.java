import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    int prev = 0;
                    if(i - 1 >= 0 && j - 1 >= 0) prev += dp[i - 1][j - 1];
                    dp[i][j] = prev + 1;
                }else{
                    int max = 0;
                    if(i - 1 >= 0) max = Math.max(max, dp[i - 1][j]);
                    if(j - 1 >= 0) max = Math.max(max, dp[i][j - 1]);
                    dp[i][j] = max;
                }
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}