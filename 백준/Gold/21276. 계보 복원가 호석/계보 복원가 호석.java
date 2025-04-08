import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static TreeMap<String, Person> tm;
    static TreeMap<String, TreeSet<String>> child;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tm = new TreeMap<>();
        child = new TreeMap<>();
        
        for(int i = 0; i < N; i++) {
            String s = st.nextToken();
            tm.put(s, new Person(s));
            child.put(s, new TreeSet<>());
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String x = st.nextToken();
            String y = st.nextToken();
            tm.get(x).up.add(tm.get(y));
            tm.get(y).down.add(tm.get(x));
        }

        StringBuilder sb = new StringBuilder();
        int root = 0;
        for(String key : tm.keySet()){
            if(tm.get(key).up.size() == 0) {
                root++;
                sb.append(key).append(" ");
                dfs(tm.get(key));
            }
        }
        sb.append("\n");
        sb.insert(0, root + "\n");

        for(String key : child.keySet()) {
            TreeSet<String> childs = child.get(key);
            sb.append(key).append(" ").append(childs.size()).append(" ");
            for(String s : childs) sb.append(s).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(Person p) {
        for(Person c : p.down) {
            if(c.up.size() == p.up.size() + 1) {
                dfs(c);

                child.get(p.name).add(c.name);
            }
        }
    }
}

class Person {
    String name;
    TreeSet<Person> up, down;

    public Person(String name) {
        this.name = name;
        up = new TreeSet<>(Comparator.comparing(p -> p.name));
        down = new TreeSet<>(Comparator.comparing(p -> p.name));
    }
}
