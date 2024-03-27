import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = br.readLine().toCharArray();
		char[] p = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		int idx = 0;
		
		while (idx < chars.length) {
			if(chars[idx] != p[p.length - 1]) stack.add(chars[idx]);
			else {
				boolean flag = true;
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < p.length - 1; i++) {
					if(stack.isEmpty()) {
						flag = false;
						break;
					}
					sb.insert(0, stack.pop());
				}
				
				if (flag) {
					for (int i = 0; i < p.length - 1; i++) {
						if (sb.charAt(i) != p[i]) {
							flag = false;
							break;
						}
					}
				}
				
				if (!flag) {
					for (int i = 0; i < sb.length(); i++) stack.add(sb.charAt(i));
					stack.add(chars[idx]);
				}
			}
			idx++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : stack) sb.append(c);
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}