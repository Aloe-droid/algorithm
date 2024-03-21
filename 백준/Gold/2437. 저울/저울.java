import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] ints = new int[N];
		for (int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(ints);
		
		int k = 0;
		for (int i = 0; i < N; i++) {
			if (k + 1 >= ints[i]) k += ints[i];
			else break;
		}
		
		System.out.println(++k);
	}
}