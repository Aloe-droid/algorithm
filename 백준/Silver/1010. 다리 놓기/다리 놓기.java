import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            BigInteger k = BigInteger.valueOf(1);

            for(int i = 0; i < n; i++) k = k.multiply(BigInteger.valueOf(m - i));
            for(int i = 0; i < n; i++) k = k.divide(BigInteger.valueOf(n - i));
            sb.append(k.intValue()).append("\n");
        }
        System.out.println(sb);
    }
}