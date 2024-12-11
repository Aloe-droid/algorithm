import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x == y) {
                sb.append(x).append("\n");
                continue;
            }

            BigInteger b1 = BigInteger.valueOf(M);
            BigInteger b2 = BigInteger.valueOf(N);
            int k = b1.gcd(b2).intValue();
            int max = M * N / k;

            int value = -1;

            int i1 = Math.max(M, N);
            int i2 = i1 == M ? x : y;
            int i3 = i1 == M ? N : M;
            int i4 = i1 == M ? y : x;

            for (int i = 0; i <= 40000; i++) {
                int ii = i2 + i1 * i;
                if (ii > max) break;

                if (ii - i4 > 0 && (ii - i4) % i3 == 0) {
                    value = ii;
                    break;
                }
            }
            sb.append(value).append("\n");
        }
        System.out.println(sb);
    }
}