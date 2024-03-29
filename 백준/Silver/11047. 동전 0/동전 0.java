import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int money = Integer.parseInt(st.nextToken());
		int[] ints = new int[N];
		for (int i = 0; i < N; i++) ints[i] = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		for (int i = N - 1; i >= 0; i--) {
			int k = money / ints[i];
			cnt += k;
			money -= k * ints[i];
		}
		System.out.println(cnt);
	}
}