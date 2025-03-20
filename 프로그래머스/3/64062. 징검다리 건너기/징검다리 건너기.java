import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> {
            if(arr1[0] == arr2[0]) return arr1[1] - arr2[1];
            else return arr2[0] - arr1[0];
        });
       
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < stones.length; i++) {
            pq.add(new int[] { stones[i], i });
            while(!pq.isEmpty() && pq.peek()[1] <= i - k) pq.poll();
            if(i >= k - 1) min = Math.min(min, pq.peek()[0]);
        }
        
        return min;
    }
}