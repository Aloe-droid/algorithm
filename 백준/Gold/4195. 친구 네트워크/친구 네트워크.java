import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Map<String, String> hm;
    static Map<String, Integer> cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            hm = new HashMap<>();
            cnt = new HashMap<>();

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String s1 = st.nextToken();
                String s2 = st.nextToken();

                if(!hm.containsKey(s1)){
                    hm.put(s1, s1);
                    cnt.put(s1, 1);
                }

                if(!hm.containsKey(s2)) {
                    hm.put(s2, s2);
                    cnt.put(s2, 1);
                }
                sb.append(union(s1, s2)).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static int union(String s1, String s2) {
        String pS1 = find(s1);
        String pS2 = find(s2);

        hm.put(pS1, pS2);
        if(pS1.equals(pS2)) return cnt.get(pS1);

        cnt.put(pS2, cnt.get(pS2) + cnt.get(pS1));
        return cnt.get(pS2);
    }

    public static String find(String s) {
        if(hm.get(s) != null && hm.get(s).equals(s)) return s;

        hm.put(s, find(hm.get(s)));
        return hm.get(s);
    }
}