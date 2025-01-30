import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static PriorityQueue<Machine> pq1, pq2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pq1 = new PriorityQueue<>((m1, m2) -> {
           if(m1.w == m2.w) return m1.mid - m2.mid;
           return m1.w - m2.w;
        });
        pq2 = new PriorityQueue<>((m1, m2) -> {
            if(m1.w == m2.w) return m2.mid - m1.mid;
            return m1.w - m2.w;
        });

        for(int i = 1; i <= K; i++) pq1.add(new Machine(i, 0, 0));
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cid = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Machine m = pq1.poll();
            if(m != null) {
                Machine mm = new Machine(m.mid, cid, m.w + w);
                pq1.add(mm);
                pq2.add(mm);
            }
        }

        long l = 0;
        long cnt = 1;
        while(!pq2.isEmpty()) {
            Machine m = pq2.poll();
            l += m.cid * cnt;
            cnt += 1;
        }
        System.out.println(l);
    }
}

class Machine {
    int mid, cid, w;

    public Machine(int mid, int cid, int w) {
        this.mid = mid;
        this.cid = cid;
        this.w = w;
    }
}