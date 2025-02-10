class Solution {
    static int N, cnt;
    static char[] chars;
    static boolean[] visit;
    static String[] data;
    
    public int solution(int n, String[] data) {
        Solution.data = data;
        chars = new char[] { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        N = chars.length;
        visit = new boolean[N];
        
        cnt = 0;
        dfs(0, new char[N]);
        return cnt;
    }
    
    public static void dfs(int idx, char[] temp) {
        if(idx == N) {
            check(temp);
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(visit[i]) continue;
            
            visit[i] = true;
            temp[idx] = chars[i];
            dfs(idx + 1, temp);
            visit[i] = false;
        }
    }
    
    public static void check(char[] temp) {
        for(String d : data) {
            char c1 = d.charAt(0);
            char c2 = d.charAt(2);
            
            int i1 = 0, i2 = 0;
            for(int i = 0; i < N; i++) {
                if(temp[i] == c1) i1 = i;
                if(temp[i] == c2) i2 = i;
            }
            
            int diff = Math.abs(i1 - i2) - 1;
            int goal = d.charAt(4) - '0';
            
            char op = d.charAt(3);
            if(op == '=' && diff != goal) return;
            if(op == '>' && diff <= goal) return;
            if(op == '<' && diff >= goal) return;
        }
        cnt++;
    }
}