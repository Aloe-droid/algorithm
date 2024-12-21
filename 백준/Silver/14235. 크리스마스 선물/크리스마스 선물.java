import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if(k == 0) {
                if(pq.isEmpty()) sb.append("-1\n");
                else sb.append(pq.poll()).append("\n");
            }else {
                for(int j = 0; j < k; j++) pq.add(Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(sb);
    }
}