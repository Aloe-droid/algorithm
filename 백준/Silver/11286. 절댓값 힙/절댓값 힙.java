import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> {
            int k1 = Math.abs(i1);
            int k2 = Math.abs(i2);
            if (k1 == k2) return i1 - i2;
            else return k1 - k2;
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(br.readLine());
            if(key != 0){
                pq.add(key);
            }else{
                if(pq.isEmpty()) sb.append(0);
                else sb.append(pq.poll());
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}