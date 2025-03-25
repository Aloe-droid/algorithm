import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Line>> list;
    static PriorityQueue<Line> pq;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Line(y, w));
            list.get(y).add(new Line(x, w));
        }
        System.out.println(bfs(1));
    }

    public static int bfs(int init) {
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.w));
        visit = new boolean[N + 1];
        
        pq.add(new Line(init, 0));
        int cnt = 0;
        int sum = 0;
        while(!pq.isEmpty() && cnt < N) {
            Line l = pq.poll();
            if(visit[l.n]) continue;
            cnt += 1;
            sum += l.w;
            visit[l.n] = true;

            for(Line next : list.get(l.n)) {
                if(visit[next.n]) continue;
                pq.add(next);
            }
        }
        return sum;
    }
}

class Line {
    int n, w;
    public Line(int n, int w) {
        this.n = n;
        this.w = w;
    }
}
