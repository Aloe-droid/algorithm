import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints, ans;
    static Map<Integer, Integer> hm;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hm = new HashMap<>();
        ints = new int[N];
        ans = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
            hm.put(ints[i], i);
        }

        for(int k : hm.keySet()){
            for(int i = 2; i <= (int) Math.sqrt(k); i++){
                if(k % i != 0) continue;

                if(hm.containsKey(i)){
                    ans[hm.get(i)]++;
                    ans[hm.get(k)]--;
                }

                if(hm.containsKey(k / i) && i != k / i){
                    ans[hm.get(k / i)]++;
                    ans[hm.get(k)]--;
                }
            }
        }

        if(hm.containsKey(1)){
            int x = hm.get(1);
            ans[x] += N;
            for(int i = 0; i < N; i++) ans[i]--;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(ans[i]).append(" ");
        System.out.println(sb);
    }
}