import java.io.*;
import java.util.*;

class Main {
    static int N = 7, cnt = 0;
    static char[][] chars;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        chars = new char[5][5];

        for(int i = 0; i < 5; i++) chars[i] = br.readLine().toCharArray();
        dfs(0, 0, new int[N]);
        System.out.println(cnt);
    }

    public static void dfs(int start, int idx, int[] ints) {
        if(idx == N) {
            if(isNear(ints)) cnt++;
            return;
        }

        for(int i = start; i < 25; i++) {
            ints[idx] = i;
            dfs(i + 1, idx + 1, ints);
        }
    }

    public static boolean isNear(int[] ints) {
        boolean[] visit = new boolean[N];
        List<int[]> list = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < N; i++) list.add(convert(ints[i]));

        if(!canTeam(list)) return false;
        
        q.add(list.get(0));
        visit[0] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                for(int j = 0; j < N; j++) {
                    if(visit[j]) continue;

                    if(list.get(j)[0] == nx && list.get(j)[1] == ny) {
                        visit[j] = true;
                        q.add(list.get(j));
                        break;
                    }
                }
            }
        }

        for(boolean v : visit) if(!v) return false; 
        return true;
    }

    public static boolean canTeam(List<int[]> list){
        int cnt = 0;
        for(int[] p : list) {
            char c = chars[p[0]][p[1]];
            if(c == 'S') cnt++;
        }
        return cnt >= 4;
    }

    public static int[] convert(int i) {
        return new int[] { i / 5, i % 5 };
    }
}