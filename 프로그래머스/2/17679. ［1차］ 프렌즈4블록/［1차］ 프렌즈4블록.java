import java.util.*;

class Solution {
    static int[] dx = {0, 1, 1}, dy = {1, 0, 1};
    public int solution(int m, int n, String[] board) {
        boolean[][] check = new boolean[m][n];
        char[][] chars = new char[m][n];
        for(int i = 0; i < m; i++) chars[i] = board[i].toCharArray();
        List<Queue<Character>> list = new ArrayList<>();
        for(int i = 0; i < n; i++) list.add(new LinkedList<>());
        
        while(true) {
            boolean canRemove = false;
            
            // 지워질 수 있는 것들 제거 
            for(int i = 0; i < m - 1; i++) {
                for(int j = 0; j < n - 1; j++) {
                    char c = chars[i][j];
                    if(c == '!') continue;
                    
                    boolean flag = true;
                    for(int k = 0; k < 3; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(chars[nx][ny] != c) {
                            flag = false;
                            break;
                        }
                    }
                    
                    if(flag) {
                        for(int k = 0; k < 3; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            check[nx][ny] = true;
                        }
                        check[i][j] = true;
                        canRemove = true;
                    }
                }
            }
            
            if(!canRemove) break;
            // 새로 담기 
            for(int i = 0; i < n; i++) {
                for(int j = m - 1; j >= 0; j--) {
                    if(check[j][i]) continue;
                    list.get(i).add(chars[j][i]);
                }
            }
            
            char[][] temp = new char[m][n];
            for(int i = 0; i < n; i++) {
                for(int j = m - 1; j >= 0; j--) {
                    if(list.get(i).isEmpty()) temp[j][i] = '!';
                    else temp[j][i] = list.get(i).poll();
                }
            }
            
            // 초기화 
            chars = temp;
            for(int i = 0; i < m; i++) Arrays.fill(check[i], false);
        }
        
        int cnt = 0;
        for(char[] cs : chars) {
            for(char c : cs) {
                if(c == '!') cnt++;
            }
        }
        
        return cnt;
    }
}