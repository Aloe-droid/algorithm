import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<List<Integer>> l1, l2;
    static Queue<Integer> q;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            l1.add(new ArrayList<>());
            l2.add(new ArrayList<>());
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            l1.get(x).add(y);
            l2.get(y).add(x);
        }

        q = new LinkedList<>();
        for(int i = 1; i <= N; i++) sb.append(bfs(i)).append("\n");
        System.out.println(sb);
    }

    public static int bfs(int init) {
        q = new LinkedList<>();
        visit = new boolean[N + 1];
        
        q.add(init);
        visit[init] = true;
        while(!q.isEmpty()) {
            int x = q.poll();
            for(int nx : l1.get(x)) {
                if(visit[nx]) continue;
                
                visit[nx] = true;
                q.add(nx);
            }
        }

        q.add(init);
        while(!q.isEmpty()) {
            int x = q.poll();
            for(int nx : l2.get(x)) {
                if(visit[nx]) continue;

                visit[nx] = true;
                q.add(nx);
            }
        }

        int cnt = 0;
        for(boolean b : visit) if(!b) cnt++;
        return cnt - 1;
    }

}