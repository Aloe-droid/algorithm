import java.io.*;
import java.util.*;

class Main {
    static TreeMap<Integer, Integer> tm;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc ++) {
            int N = Integer.parseInt(br.readLine());
            tm = new TreeMap<>();
            
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int v = Integer.parseInt(st.nextToken());

                if(op.equals("I")){
                    tm.put(v, tm.getOrDefault(v, 0) + 1);
                    continue;
                }

                if(tm.size() == 0) continue;
                
                int key = v == 1 ? tm.lastKey() : tm.firstKey();
                tm.put(key, tm.get(key) - 1);
                if(tm.get(key) == 0) tm.remove(key);
            }

            if(tm.size() == 0) sb.append("EMPTY\n");
            else sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
        }
        
        System.out.println(sb);
    }
}