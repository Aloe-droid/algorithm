import java.io.*;

class Main {
    static int N;
    static boolean[][] boolArray;
    static int MIN;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MIN = Integer.MAX_VALUE;
        boolArray = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                boolArray[i][j] = s.charAt(j) != 'H';
            }
        }
        combination(0);
        System.out.println(MIN);
    }

    public static void combination(int start) {
        if (start > N) return;

        for (int i = start; i < N; i++) {
            // 토글링
            toggle(i, true);
            combination(i + 1);
            // 원상복귀
            toggle(i, false);
        }
    }

    public static void toggle(int idx, boolean flag) {
        for (int i = 0; i < N; i++) boolArray[i][idx] = !boolArray[i][idx];
        if (flag) check();
    }

    public static void check() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int rowCnt = 0;
            for (int j = 0; j < N; j++) {
                if (boolArray[i][j]) rowCnt++;
            }
            cnt += Math.min(rowCnt, N - rowCnt);
        }
        MIN = Math.min(MIN, cnt);
    }
}