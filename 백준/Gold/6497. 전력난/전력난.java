import java.io.*;
import java.util.*;

class Main {
    static int N, M; 
    static long min, max = 0L;
    static List<List<Line>> list;
    static boolean[] visit;
    static PriorityQueue<Line> pq;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;
            
            visit = new boolean[N];
            pq = new PriorityQueue<>(Comparator.comparingLong(l -> l.w));
            list = new ArrayList<>();
            for(int i = 0; i < N; i++) list.add(new ArrayList<>());
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                list.get(x).add(new Line(y, w));
                list.get(y).add(new Line(x, w));
                max += w;
            }
    
            min = find();
            sb.append(max - min).append("\n");
            max = 0;
        }
        System.out.println(sb);
    }

    public static long find() {
        pq.add(new Line(0, 0));
        
        int cnt = 0;
        long value = 0;

        while(!pq.isEmpty()) {
            if(cnt == N) break;
            Line line = pq.poll();
            
            if(visit[line.id]) continue;
            visit[line.id] = true;
            value += line.w;
            cnt += 1;
            
            for(Line next : list.get(line.id)) {
                if(visit[next.id]) continue;
                pq.add(next);
            }
        }
        return value;
    }
}

class Line {
    int id;
    long w;
    public Line(int id, long w) {
        this.id = id;
        this.w = w;
    }
}