import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int max;
	static char[] chars;
	static Set<String> hs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		hs = new HashSet<>();
		chars = st.nextToken().toCharArray();
		K = Integer.parseInt(st.nextToken());
		max = -1;
		dfs(0);
		System.out.println(max);
	}

	public static void dfs(int k) {
		if (k == K) {
			max = Math.max(max, Integer.parseInt(String.valueOf(chars)));
			return;
		}
		
		for (int i = 0; i < chars.length - 1; i++) {
			for (int j = i + 1; j < chars.length; j++) {
				if (i == 0 && chars[j] == '0') continue;
				
				char temp = chars[i];
				chars[i] = chars[j];
				chars[j] = temp;
				String s = String.valueOf(chars).concat(Integer.toString(k));
				
				if (!hs.contains(s)) {
					hs.add(s);
					dfs(k + 1);
				}
				
				temp = chars[i];
				chars[i] = chars[j];
				chars[j] = temp;
			}
		}
	}
}