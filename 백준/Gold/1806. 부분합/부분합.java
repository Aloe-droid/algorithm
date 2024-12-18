import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ints = new int[N];
        int[] sum = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());
        sum[0] = ints[0];
        for (int i = 1; i < N; i++) sum[i] = ints[i] + sum[i - 1];

        int left = 0, right = 0;
        int diff = Integer.MAX_VALUE;

        while (left < N && right < N) {
            int temp;

            if(left == 0) temp = sum[right];
            else temp = sum[right] - sum[left - 1];

            if(temp >= M) diff = Math.min(diff, right - left + 1);

            if(temp >= M) left++;
            else right++;
        }

        System.out.println(diff == Integer.MAX_VALUE ? 0 : diff);
    }
}