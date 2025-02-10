import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int N = book_time.length;
        int[][] ints = new int[N][2];
        for(int i = 0; i < N; i++) {
            ints[i][0] = convert(book_time[i][0]);
            ints[i][1] = convert(book_time[i][1]) + 10;
        }
        
        Arrays.sort(ints, (i1, i2) -> {
            if(i1[0] == i2[0]) return i1[1] - i2[1];
            else return i1[0] - i2[0];
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            int start = ints[i][0];
            int end = ints[i][1];
            if(!pq.isEmpty() && pq.peek() <= start) pq.poll();
            pq.add(end);
        }
        
        return pq.size();
    }
    
    public int convert(String t) {
        StringTokenizer st = new StringTokenizer(t, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return hour * 60 + minute;
    }
}