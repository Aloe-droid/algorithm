import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int max = 100_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] ints = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                if(ints[i][j] == 0) ints[i][j] = max;
            }
        }


        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ints[i][j] = Math.min(ints[i][j], ints[i][k] + ints[k][j]);
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(ints[i][j] == max) ints[i][j] = 0;
                else ints[i][j] = 1;
                sb.append(ints[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}