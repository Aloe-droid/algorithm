import java.util.*;

class Solution {
    static int[] dx = { 1,-1, 0, 0};
    static int[] dy = { 0, 0,-1, 1};
    
    public int solution(String dirs) {
        Set<String> hs = new HashSet<>();
        int x = 0, y = 0;
        
        for(int i = 0; i < dirs.length(); i++){
            char c = dirs.charAt(i);
            int k = 0;
            
            if(c == 'U') k = 0;
            else if(c == 'D') k = 1;
            else if(c == 'L') k = 2;
            else if(c == 'R') k = 3;
            
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
            
            String s1 = x + ", " + y + " -> " + nx + ", " + ny;
            String s2 = nx + ", " + ny + " -> " + x + ", " + y;
            hs.add(s1);
            hs.add(s2);
            
            x = nx;
            y = ny;
        }
        
        
        return hs.size() / 2;
    }
}