import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> list = new ArrayList<>();
        int[] ints = new int[n + 1];
        for(int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for(int[] ii : edge){
            int x = ii[0];
            int y = ii[1];
            list.get(x).add(y);
            list.get(y).add(x);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        ints[1] = 1;
        
        while(!q.isEmpty()){
            int x = q.poll();
            for(int nx : list.get(x)){
                if(ints[nx] != 0) continue;
                q.add(nx);
                ints[nx] = ints[x] + 1;
            }
        }
        
        int max = -1;
        int cnt = 0;
        for(int i = 1; i <= n; i++) max = Math.max(max, ints[i]);
        for(int i = 1; i <= n; i++) if(ints[i] == max) cnt++;
        return cnt;        
    }
}