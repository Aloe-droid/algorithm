import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            
            for(int i = 0; i < s.length(); i++) {
                for(int j = 0; j < k; j++) {
                    sb.append(s.charAt(i) + "");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}