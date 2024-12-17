import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ints = new int[N];
        for (int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = ints.length - 1;
        int min = Integer.MAX_VALUE;
        int ansLeft = 0, ansRight = 0;

        while (left < right) {
            int value = ints[left] + ints[right];
            int absValue = Math.abs(value);

            if(absValue < min){
                min = absValue;
                ansLeft = left;
                ansRight = right;
            }

            if (value > 0) right--;
            else left++;
        }

        System.out.println(ints[ansLeft] + " " + ints[ansRight]);
    }
}