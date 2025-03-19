import java.math.*;

class Solution {
    int n;
    int[] times;
    
    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;
        long left = 1, right = (long) Math.pow(10, 18);
        while(left <= right) {
            long mid = getMid(left, right);
            
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
    
    public long getMid(long l1, long l2) {
        BigInteger b1 = BigInteger.valueOf(l1);
        BigInteger b2 = BigInteger.valueOf(l2);
        BigInteger b3 = b1.add(b2).divide(BigInteger.valueOf(2));
        return b3.longValue();
    }
}