import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split("[+-]");
		int ans = Integer.parseInt(split[0]);
		boolean flag = false;
		int k = split[0].length();

		for (int i = 1; i < split.length; i++) {
			int n = Integer.parseInt(split[i]);
			if(s.charAt(k) == '+' && !flag) {
				ans += n;
			}else {
				flag = true;
				ans -= n;
			}
			k += split[i].length() + 1;
		}
		System.out.println(ans);
	}
}