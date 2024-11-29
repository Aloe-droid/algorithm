import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        List<List<Integer>> list = new ArrayList<>();
        boolean[] bools = new boolean[n + 1];
        for(int i = 0; i <= n; i++) list.add(new ArrayList<>());
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                if(computers[i][j] == 0) continue;
                list.get(i + 1).add(j + 1);
            }
        }
        
        int cnt = 0;
        for(int i = 1; i <= n; i++){
            if(bools[i]) continue;
            cnt++;
            
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            bools[i] = true;
            
            while(!q.isEmpty()){
                int x = q.poll();
                for(int nx : list.get(x)){
                    if(bools[nx]) continue;
                    q.add(nx);
                    bools[nx] = true;
                }
            }
        }
        return cnt;
    }
}