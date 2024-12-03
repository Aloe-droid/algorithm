class Solution {
    public int solution(int n) {
        int[] dp = new int[n + 1];
        for(int i = 1; i < dp.length; i++) dp[i] = dp[i - 1] + i;
        
        int cnt = 0;
        for(int i = 2; i < n; i++){
            int mid = n / i;
            int left = mid - (i / 2) - (i % 2);
            int right = mid + (i / 2);
            if(left < 0 || right >= n) break;
            
            if(dp[right] - dp[left] == n) cnt++;
        }
        
        return cnt + 1;
    }
}