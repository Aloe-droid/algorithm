import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static God[] gods;
    static List<List<Integer>> list;
    static PriorityQueue<double[]> pq;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gods = new God[N + 1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = new God(x, y);
        }

        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        String s = String.format("%.2f", find());
        System.out.println(s);
    }

    public static double find () {
        pq = new PriorityQueue<>((arr1, arr2) -> {
            if(arr1[1] == arr2[1]) return 0;
            else if(arr1[1] < arr2[1]) return -1;
            else return 1;
        });
        visit = new boolean[N + 1];

        int cnt = 0;
        double sum = 0;
        pq.add(new double[] {1, 0});
        while(!pq.isEmpty()) {
            int id = (int) pq.peek()[0];
            double w = pq.poll()[1];

            if(visit[id]) continue;
            visit[id] = true;
            sum += w;
            if(++cnt == N) break;

            for(int near : list.get(id)) {
                if(visit[near]) continue;
                pq.add(new double[] { (double) near, 0 });
            }

            for(int i = 1; i <= N; i++) {
                if(visit[i]) continue;
                pq.add(new double[] { (double) i, getD(id, i) });
            }
        }

        return sum;
    }

    public static double getD(int i1, int i2) {
        God g1 = gods[i1];
        God g2 = gods[i2];
        double diffX = Math.pow(Math.abs(g1.x - g2.x), 2);
        double diffY = Math.pow(Math.abs(g1.y - g2.y), 2);
        return Math.sqrt(diffX + diffY);
    }
}

class God {
    int x, y;
    public God(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
