import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] ints = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (chars[j] == 'B') ints[i][j] = 0;
                else ints[i][j] = 1;
            }
        }

        int minCnt = Integer.MAX_VALUE;
        int count = 0;
        boolean isFirst = true;
        int prev = -1;

        // 체스판의 초기 위치 값 변경
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                // 8x8 체스판
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {

                        if (isFirst) {
                            // 처음인 경우엔 prev 가 없음!
                            prev = ints[i + k][j + l];
                            isFirst = false;
                        } else {
                            // 이전값과 다른지 확인
                            boolean isDiff = true;

                            int newInt = ints[i + k][j + l];

                            if (l == 0 && prev != newInt) count++;
                            else if (l != 0 && prev == newInt) count++;
                            else isDiff = false;

                            if (!isDiff) prev = newInt;
                            else {
                                // 만약 다른 값이였다면 이상적인 값으로 prev 를 변경
                                if (newInt == 1) prev = 0;
                                else prev = 1;
                            }
                        }
                    }
                }

                count = Math.min(count, 64 - count);

                if (minCnt > count) {
                    minCnt = count;
                }

                // 변수 초기화
                count = 0;
                isFirst = true;
                prev = -1;
            }
        }

        System.out.println(minCnt);
    }
}
