class Solution {
    public int solution(int[] money) {
        if(money.length == 1) return money[0];
        int N = money.length;
        int[] dp1 = new int[N], dp2 = new int[N];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        dp2[1] = money[1];
        
        for(int i = 2; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + (i >= N - 1 ? 0 : money[i]));
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        return Math.max(dp1[N - 1], dp2[N - 1]);
    }
}