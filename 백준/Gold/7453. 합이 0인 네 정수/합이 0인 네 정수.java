import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] longs = new long[4][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) longs[j][i] = Long.parseLong(st.nextToken());
        }

        int len = N * N;
        long[] l1 = new long[len];
        long[] l2 = new long[len];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                int idx = i * N + j;
                l1[idx] = longs[0][i] + longs[1][j];
                l2[idx] = longs[2][i] + longs[3][j];
            }
        }

        Arrays.sort(l1);
        Arrays.sort(l2);

        long cnt = 0L;
        int i1 = 0;
        int i2 = len - 1;

        while(i1 < len && i2 >= 0) {
            long value = l1[i1] + l2[i2];

            if(value > 0) i2--;
            else if(value < 0) i1++;
            else {
                long temp1 = 1L;
                long temp2 = 1L;

                while(i1 + 1 < len && l1[i1] == l1[i1 + 1]) {
                    i1++;
                    temp1++;
                }

                while(i2 - 1 >= 0 && l2[i2] == l2[i2 - 1]) {
                    i2--;
                    temp2++;
                }

                cnt += temp1 * temp2;
                // 아무거나 변화
                i1++;
            }
        }
        System.out.println(cnt);
    }
}



