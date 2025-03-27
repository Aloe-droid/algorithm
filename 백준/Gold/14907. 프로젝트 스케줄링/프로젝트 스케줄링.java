import java.io.*;
import java.util.*;

class Main {
    static int N = 26;
    static int[] inDegrees, works, dp;
    static List<List<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inDegrees = new int[N];
        works = new int[N];
        dp = new int[N];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
            dp[i] = -1;
        }

        while(true) {
            String s = br.readLine();
            if(s == null || s.isEmpty() || s.isBlank()) break;
            StringTokenizer st = new StringTokenizer(s);
            int id = st.nextToken().charAt(0) - 'A';
            int work = Integer.parseInt(st.nextToken());
            works[id] = work;

            if(st.hasMoreTokens()) {
                char[] chars = st.nextToken().toCharArray();
                for(char c : chars) {
                    int cId = c - 'A';
                    list.get(cId).add(id);
                    inDegrees[id]++;
                }
            }
            
        }

        int time = 0;
        for(int i = 0; i < N; i++) {
            if(inDegrees[i] != 0) continue;
            time = Math.max(time, dfs(i));
        }
        System.out.println(time);
    }

    public static int dfs(int x) {
        if(dp[x] != -1) return dp[x];

        dp[x] = works[x];
        int temp = 0;
        for(int nx : list.get(x)) {
            temp = Math.max(temp, dfs(nx));
        }
        return dp[x] += temp;
    }
}
