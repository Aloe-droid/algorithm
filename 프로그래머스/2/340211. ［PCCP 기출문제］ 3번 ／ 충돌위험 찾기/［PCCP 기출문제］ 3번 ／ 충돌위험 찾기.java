import java.util.*;

class Solution {
    static int[][] points;
    static int[] dx = {1, -1 ,0, 0}, dy = {0, 0, 1, -1};
    static List<List<int[]>> list;
    
    public int solution(int[][] points, int[][] routes) {
        Solution.points = points;
        list = new ArrayList<>();
        for(int i = 0; i < routes.length; i++) {
            list.add(new ArrayList<>());
            bfs(i, routes[i]);
        }
        
        return check();
    }
    
    public static int check() {
        int cnt = 0;
        int lastT = 0;
        for(List<int[]> ll : list) lastT = Math.max(lastT, ll.size());
        
        for(int t = 0; t < lastT; t++) {
            int[] ints = new int[10_101];
            
            for(int idx = 0; idx < list.size(); idx++) {
                if(list.get(idx).size() <= t) continue;
                int[] loc = list.get(idx).get(t);
                int c = loc[0] * 100 + loc[1];
                ints[c]++;
            }
            
            for(int i : ints) if(i >= 2) cnt++;
        }
        return cnt;
    }
    
    public static void bfs(int idx, int[] route) {
        for(int i = 0; i < route.length - 1; i++) {
            int[] s = points[route[i] - 1];
            int[] e = points[route[i + 1] - 1];
            
            boolean[][] check = new boolean[101][101];
            Queue<int[]> q = new LinkedList<>();
            
            q.add(s);
            check[s[0]][s[1]] = true;
            list.get(idx).add(new int[] { s[0], s[1] });
            
            while(!q.isEmpty()) {
                int x = q.peek()[0];
                int y = q.poll()[1];
                
                int minDiff = Integer.MAX_VALUE;
                int kk = -1;
                for(int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    
                    if(nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
                    if(check[nx][ny]) continue;
                    
                    if(Math.abs(nx - e[0]) + Math.abs(ny - e[1]) < minDiff) {
                        minDiff = Math.abs(nx - e[0]) + Math.abs(ny - e[1]);
                        kk = k;
                    }
                }
                
                if(kk != -1) {
                    int nx = x + dx[kk];
                    int ny = y + dy[kk];
                    q.add(new int[] { nx, ny });
                    check[nx][ny] = true;
                    
                    if(nx != e[0] || ny != e[1]) list.get(idx).add(new int[] { nx, ny});
                    else break;
                }
            }
        }
        
        int[] last = points[route[route.length - 1] - 1];
        list.get(idx).add(new int[] { last[0], last[1] });
    }
    
}