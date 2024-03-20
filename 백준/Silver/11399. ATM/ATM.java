import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] ints = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(ints);
		int sum = 0;
		for (int i = 1; i < N; i++) ints[i] = ints[i] + ints[i - 1];
		for(int i : ints) sum += i;
		System.out.println(sum);
	}
}