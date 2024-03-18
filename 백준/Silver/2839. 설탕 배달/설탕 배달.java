import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        int maxFive = -1;
        int maxThree = -1;

        for (int i = num; i >= 0; i -= 3) {
            if (i % 5 != 0) continue;

            int fiveCnt = i / 5;
            int threeCnt = (num - i) / 3;

            if (fiveCnt > maxFive) {
                maxFive = fiveCnt;
                maxThree = threeCnt;
            }
        }

        if (maxFive == -1) System.out.println(-1);
        else System.out.println(maxFive + maxThree);
    }
}
