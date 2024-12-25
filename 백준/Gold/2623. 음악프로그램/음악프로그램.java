import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<Set<Integer>> inDegrees, outDegrees;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        inDegrees = new ArrayList<>();
        outDegrees = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            outDegrees.add(new HashSet<>());
            inDegrees.add(new HashSet<>());
        }

        for(int i = 0; i < M; i++){
            String[] ss = br.readLine().split(" ");
            for(int j = 1; j < ss.length - 1; j++){
                int x = Integer.parseInt(ss[j]);
                int y = Integer.parseInt(ss[j + 1]);
                outDegrees.get(x).add(y);
                inDegrees.get(y).add(x);
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) if(inDegrees.get(i).isEmpty()) q.add(i);

        Set<Integer> hs = new HashSet<>();
        while(!q.isEmpty()){
            int x = q.poll();
            if(hs.contains(x)) continue;
            hs.add(x);
            sb.append(x).append("\n");
            for(int nx : outDegrees.get(x)){
                inDegrees.get(nx).remove(x);
                if(inDegrees.get(nx).isEmpty()) q.add(nx);
            }
        }

        if(hs.size() == N) System.out.println(sb);
        else System.out.println(0);
    }
}