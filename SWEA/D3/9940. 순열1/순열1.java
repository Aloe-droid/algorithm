import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= T; tc++){
            int a = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            int[] ints = new int[a];
            for (int i = 0; i < a; i++) {
                ints[i] = i + 1;
                pq.add(Integer.parseInt(st.nextToken()));
            }

            sb.append("#").append(tc);
            if(isSame(ints, pq)) sb.append(" Yes");
            else sb.append(" No");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static boolean isSame(int[] ints, PriorityQueue<Integer> pq) {
        for(int i : ints){
            if(i != pq.poll()) return false;
        }
        return true;
    }
}