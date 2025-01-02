import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Jewel[] jewels = new Jewel[N];
        int[] bags = new int[K];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());

        }
        Arrays.sort(jewels, (j1, j2) -> {
           if(j1.m == j2.m) return j2.v - j1.v;
           else return j1.m - j2.m;
        });

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0L;
        int idx = 0;
        for(int bag : bags) {
            while(idx < N && jewels[idx].m <= bag) {
                pq.add(jewels[idx].v);
                idx++;
            }

            if(!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);
    }
}

class Jewel {
    int m, v;
    public Jewel(int m, int v) {
        this.m = m;
        this.v = v;
    }
}