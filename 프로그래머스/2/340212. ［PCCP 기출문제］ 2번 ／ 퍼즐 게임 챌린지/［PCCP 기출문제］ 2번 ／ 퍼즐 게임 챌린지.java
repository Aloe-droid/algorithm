class Solution {
    static long limit;
    static int[] diffs, times;
    public int solution(int[] diffs, int[] times, long limit) {
        Solution.diffs = diffs;
        Solution.times = times;
        Solution.limit = limit;
        
        int left = 1;
        int right = 100_000;

        while(left <= right) {
            int mid = (left + right) / 2;
            boolean bool = test(mid);
            
            if(bool) right = mid - 1;
            else left = mid + 1; 
        }
        return left;
    }
    
    public static boolean test(int level) {
        long tt = 0L;
        
        for(int i = 0; i < diffs.length; i++) {
            if(level >= diffs[i]) tt += times[i];
            else tt += (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
     
            if(tt > limit) return false;
        }
        return true;
    }
}