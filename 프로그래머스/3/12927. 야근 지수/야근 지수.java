import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) pq.add(work);
        
        while(!pq.isEmpty() && n > 0) {
            int w = pq.poll();
            n -= 1;
            if(w - 1 > 0) pq.add(w - 1);
        }
        
        long ans = 0;
        while(!pq.isEmpty()) ans += (long) Math.pow(pq.poll(), 2);
        return ans;
    }
}