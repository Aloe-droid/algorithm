import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static List<List<Line>> list;
    static long[] visit;
    static Queue<Line> q;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            String[] ss = br.readLine().split(" ");
            int prev = Integer.parseInt(ss[0]);

            for (int j = 1; j < ss.length - 2; j += 2) {
                int now = Integer.parseInt(ss[j]);
                long w = Long.parseLong(ss[j + 1]);
                list.get(prev).add(new Line(prev, now, w));
            }
        }

        int r1 = bfs(1);
        int r2 = bfs(r1);
        System.out.println(visit[r2]);
    }

    public static int bfs(int init) {
        q = new LinkedList<>();
        visit = new long[N + 1];
        Arrays.fill(visit, -1);

        q.add(new Line(0, init, 0));
        visit[0] = 0;
        while (!q.isEmpty()) {
            Line line = q.poll();

            if (visit[line.now] != -1) continue;
            visit[line.now] = visit[line.prev] + line.w;

            for (Line next : list.get(line.now)) {
                if (visit[next.now] != -1) continue;
                q.add(next);
            }
        }

        long max = -1;
        int maxIdx = 0;
        for(int i = 1; i <= N; i++) {
            if(visit[i] > max) {
                max = visit[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}

class Line {
    int prev, now;
    long w;

    public Line(int prev, int now, long w) {
        this.prev = prev;
        this.now = now;
        this.w = w;
    }
}
