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

        StringBuilder sb = new StringBuilder();
        int i = n - 1, j = m - 1;
        while(i >= 0 && j >= 0){
            if(s1.charAt(i) == s2.charAt(j)){
                sb.append(s1.charAt(i));
                i -= 1;
                j -= 1;
            }else{
                if(i - 1 < 0) j -= 1;
                else if(j - 1 < 0) i -= 1;
                else if(dp[i][j - 1] > dp[i - 1][j]) j -= 1;
                else i -= 1;
            }
        }

        System.out.println(sb.length());
        System.out.println(sb.reverse());
    }
}