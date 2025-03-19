import java.math.*;

class Solution {
    int n;
    int[] times;
    
    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;
        long left = 1, right = (long) Math.pow(10, 18);
        while(left <= right) {
            long mid = (left + right) / 2;
            
            if(check(mid)) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
    
    public boolean check(long mid) {
        long cnt = 0;
        for(int time : times) cnt += mid / time;
        return cnt >= n;
    }
}