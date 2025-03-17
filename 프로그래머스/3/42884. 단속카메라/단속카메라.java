import java.util.*;

class Solution {
    static PriorityQueue<int[]> pq;
    static int cnt;
    public int solution(int[][] routes) {
        init(routes);
        find();
        return cnt;
    }
    
    public void init(int[][] routes) {
        cnt = 1;
        pq = new PriorityQueue<>((arr1, arr2) -> {
            if(arr1[0] == arr2[0]) return arr1[1] - arr2[1];
            else return arr1[0] - arr2[0];
        });
        
        for(int[] route : routes) pq.add(route);
    }
    
    public void find() {
        int prev = pq.poll()[1];
        
        while(!pq.isEmpty()) {
            int st = pq.peek()[0];
            int ed = pq.poll()[1];
        
            if(prev >= st && prev <= ed) continue;
            if(prev >= st && prev >= ed) {
                prev = ed;
                continue;
            }
            
            cnt++;
            prev = ed;
        }
    }
}