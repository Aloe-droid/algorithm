import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int[][] map = new int[maps.length][maps[0].length()];
        boolean[][] check = new boolean[map.length][map[0].length];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for(int i = 0; i < maps.length; i++){
            String s = maps[i];
            for(int j = 0; j < s.length(); j++){
                if(s.charAt(j) == 'X') map[i][j] = -1;
                else map[i][j] = s.charAt(j) - '0';
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == -1 || check[i][j]) continue;
                
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] {i, j});
                check[i][j] = true;
                
                int temp = 0;
                while(!q.isEmpty()){
                    int x = q.peek()[0];
                    int y = q.poll()[1];
                    temp += map[x][y];
                    
                    for(int k = 0; k < 4; k++){
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || check[nx][ny] || map[nx][ny] == -1) continue;
                        q.add(new int[] {nx, ny});
                        check[nx][ny] = true;
                    }
                }
                list.add(temp);
            }
        }
        Collections.sort(list);
        return list.isEmpty() ? new int[] { -1 } : list.stream().mapToInt(i -> i).toArray();
    }
}