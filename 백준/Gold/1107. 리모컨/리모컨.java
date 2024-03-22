import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] cantUse = new boolean[10];
        int diff = Math.abs(N - 100);
        List<Integer> canUse = new ArrayList<>();

        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                cantUse[Integer.parseInt(st.nextToken())] = true;
            }
        }

        for (int i = 0; i < cantUse.length; i++) {
            if (!cantUse[i]) {
                canUse.add(i);
            }
        }

        if (N == 100) bw.write("0");
        else if (canUse.isEmpty()) bw.write(diff + "");
        else {
            int min = diff;

            for (int i = 0; i < 1_000_000; i++) {
                String s = Integer.toString(i);
                boolean can = true;

                for (int j = 0; j < s.length(); j++) {
                    if (cantUse[s.charAt(j) - '0']) {
                        can = false;
                        break;
                    }
                }

                if (can) {
                    int len = Math.abs(i - N) + s.length();
                    min = Math.min(len, min);
                }
            }

            bw.write(min + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}