class Solution {
    static int div = 10_007;
    public int solution(int n, int[] tops) {
        int[] dp = new int[n * 2 + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i < dp.length; i++){
            try{ dp[i] += dp[i - 1]; }catch(Exception e){}
            try{ dp[i] += dp[i - 2]; }catch(Exception e){}
            if(i % 2 == 1 && tops[i/2] == 1) dp[i] += dp[i - 1];
            dp[i] %= div;
        }
        return dp[dp.length -1];
    }
}