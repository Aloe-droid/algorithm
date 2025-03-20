public class Solution {
    int N, P, Q;
    int[][] land;
    
    public long solution(int[][] land, int P, int Q) {
        N = land.length;
        this.land = land;
        this.P = P;
        this.Q = Q;
        return search();
    }
    
    public long search() {
        int left = 0, right = 1_000_000_000;
        long min = Long.MAX_VALUE;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            long v1 = getCost(mid);
            long v2 = getCost(mid + 1);
            min = Math.min(Math.min(v1, v2), min);
            
            if(v1 < v2) right = mid - 1;
            else left = mid + 1;
        }
        return min;
    }
    
    public boolean check(long mid) {
        return true;
    }
    
    public long getCost(int m) {
        long cost = 0L;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int l = land[i][j];
                if(l > m) cost += (l - m) * (long) Q;
                else if(l < m) cost += (m - l) * (long) P;
            }
        }
        return cost;
    }
}