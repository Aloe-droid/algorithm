class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] dp = new int[arr.length];
        for(int[] q : queries){
            dp[q[0]]++;
            if(q[1] + 1 < arr.length) dp[q[1] + 1]--;
        }
        
        for(int i = 1; i < dp.length; i++) dp[i] += dp[i - 1];
        for(int i = 0; i < arr.length; i++) arr[i] += dp[i];
        return arr;
    }
}