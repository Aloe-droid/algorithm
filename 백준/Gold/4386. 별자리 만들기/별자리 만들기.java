import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Star[] stars;
    static boolean[] visit;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visit = new boolean[N];
        stars = new Star[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = (int) (Float.parseFloat(st.nextToken()) * 1000);
            int y = (int) (Float.parseFloat(st.nextToken()) * 1000);
            stars[i] = new Star(i, x, y);
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        pq.add(new int[] {0, 0});

        int cost = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            int[] ints = pq.poll();
            if(visit[ints[0]]) continue;
            if(cnt == N) break;

            visit[ints[0]] = true;
            cnt++;
            cost += ints[1];

            Star star = stars[ints[0]];
            for(Star other : stars) {
                if(visit[other.id]) continue;

                int distance = getD(star, other);
                pq.add(new int[] {other.id, distance });
            }
        }

        String s = String.format("%.2f", cost / 1000f);
        System.out.println(s);
    }

    public static int getD(Star s1, Star s2) {
        double dx = Math.pow(Math.abs(s1.x - s2.x), 2);
        double dy = Math.pow(Math.abs(s1.y - s2.y), 2);
        double diff = Math.sqrt(dx + dy);
        return (int) diff;
    }
}

class Star {
    int id, x, y;
    public Star(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}