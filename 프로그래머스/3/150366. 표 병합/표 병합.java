import java.util.*;

class Solution {
    static List<String> ans;
    static Map<Integer, String> hm;
    static int len = 50;
    static int[] parents;

    public String[] solution(String[] commands) {
        ans = new ArrayList<>();
        hm = new HashMap<>();
        parents = new int[len * len];
        for (int i = 0; i < len * len; i++) parents[i] = i;

        for (String command : commands) {
            String[] commandArray = command.split(" ");
            switch (commandArray[0]) {
                case "UPDATE":
                    if (commandArray.length == 4) {
                        int r = Integer.parseInt(commandArray[1]);
                        int c = Integer.parseInt(commandArray[2]);
                        String val = commandArray[3];
                        update(r, c, val);
                    } else {
                        String s1 = commandArray[1];
                        String s2 = commandArray[2];
                        update(s1, s2);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(commandArray[1]);
                    int c1 = Integer.parseInt(commandArray[2]);
                    int r2 = Integer.parseInt(commandArray[3]);
                    int c2 = Integer.parseInt(commandArray[4]);
                    merge(r1, c1, r2, c2);
                    break;
                case "UNMERGE": {
                    int r = Integer.parseInt(commandArray[1]);
                    int c = Integer.parseInt(commandArray[2]);
                    unmerge(r, c);
                    break;
                }
                default: {
                    int r = Integer.parseInt(commandArray[1]);
                    int c = Integer.parseInt(commandArray[2]);
                    print(r, c);
                    break;
                }
            }
        }
        return ans.toArray(new String[0]);
    }

    public static void update(int r, int c, String s) {
        int x = convert(r, c);
        int p = find(x);
        hm.put(p, s);
    }

    public static void update(String s1, String s2) {
        for (int i = 0; i < len * len; i++) {
            int p = find(i);
            String s = hm.get(p);
            if (s != null && s.equals(s1)) hm.replace(p, s2);
        }
    }

    public static void merge(int r1, int c1, int r2, int c2) {
        int x1 = convert(r1, c1);
        int x2 = convert(r2, c2);
        int p1 = find(x1);
        int p2 = find(x2);
        String s1 = hm.get(p1);
        String s2 = hm.get(p2);

        if (p1 == p2) return;

        hm.remove(p1);
        hm.remove(p2);
        union(x1, x2);
        
        if (s1 != null) hm.put(p1, s1);
        else if (s2 != null) hm.put(p1, s2);
    }

    public static void unmerge(int r, int c) {
        int x = convert(r, c);
        int p = find(x);
        String s = hm.get(p);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len * len; i++) if (find(i) == p) list.add(i);
        for (int i : list) parents[i] = i;

        hm.remove(p);
        if (s != null) hm.put(x, s);
    }

    public static void print(int r, int c) {
        int x = convert(r, c);
        int p = find(x);
        String s = hm.get(p);
        ans.add(s != null ? s : "EMPTY");
    }

    public static void union(int x1, int x2) {
        int p1 = find(x1);
        int p2 = find(x2);

        parents[p2] = p1;
    }

    public static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public static int convert(int r, int c) {
        return (r - 1) * len + (c - 1);
    }

}