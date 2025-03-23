import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Word init;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        init = new Word("-");

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Word word = init;
            for(int j = 0; j < K; j++) {
                String s = st.nextToken();
                if(!word.next.containsKey(s)) word.next.put(s, new Word(s));
                word = word.next.get(s);
            }
        }

        print(init, 0);
        System.out.println(sb);
    }

    public static void print(Word word, int cnt) {
        if(!word.s.equals("-")) {
            String depth =  "-".repeat((cnt - 1) * 2);
            sb.append(depth).append(word.s).append("\n");
        }

        for(String s : word.next.keySet()) {
            print(word.next.get(s), cnt + 1);
        }
    }
}

class Word {
    String s;
    Map<String, Word> next;

    public Word(String s) {
        this.s = s;
        next = new TreeMap<>();
    }
}
