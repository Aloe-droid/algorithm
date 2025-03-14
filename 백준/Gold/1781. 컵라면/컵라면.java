import java.io.*;
import java.util.*;

class Main {
    static int N, mD;
    static PriorityQueue<Problem> pq1;
    static PriorityQueue<Long> pq2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mD = 0;

        pq1 = new PriorityQueue<>((p1, p2) -> {
            if(p1.d > p2.d) return -1;
            else if(p1.d < p2.d) return 1;
            else return 0;
        });

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            pq1.add(new Problem(d, c));
            mD = Math.max(mD, d);
        }

        pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0L;
        for(int day = mD; day > 0; day--) {
            while(!pq1.isEmpty() && pq1.peek().d == day) pq2.add(pq1.poll().c);
            if(!pq2.isEmpty()) sum += pq2.poll();
        }
        System.out.println(sum);
    }
}

class Problem {
    int d;
    long c;

    public Problem(int d, long c) {
        this.d = d;
        this.c = c;
    }
}
