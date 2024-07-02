class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0L;
        long right = (long) Math.pow(10, 15);
        
        while(left <= right){
            long sum = 0L;
            long gold = 0L;
            long silver = 0L;
            long mid = (left + right) / 2;
            
            for(int i = 0; i < t.length; i++){
                long temp = mid / t[i];
                long cnt = 0L;
                if(temp > 0){
                    cnt = 1L;
                    cnt += (temp - 1) / 2L;
                }
                
                long min = Math.min(cnt * w[i], g[i] + s[i]);
                long minG = Math.min(min, g[i]);
                long minS = Math.min(min, s[i]);
                
                sum += min;
                gold += minG;
                silver += minS;
            }
            
            if(sum >= a + b && gold >= a && silver >= b) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}