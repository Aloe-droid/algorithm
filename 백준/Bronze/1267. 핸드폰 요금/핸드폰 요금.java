import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int y = 0;
        int m = 0;

        for(int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            y += (k / 30 + 1) * 10;
            m += (k / 60 + 1) * 15;
        }

        StringBuilder sb = new StringBuilder();
        if(y <= m) sb.append("Y ");
        if(m <= y) sb.append("M ");
        sb.append(Math.min(y, m));
        System.out.println(sb);
    }
}