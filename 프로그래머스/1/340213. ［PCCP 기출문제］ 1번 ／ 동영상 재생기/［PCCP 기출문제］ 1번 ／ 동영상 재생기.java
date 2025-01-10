import java.util.*;

class Solution {
    static int len, pos, os, oe;
    public String solution(String vv, String pp, String ss, String ee, String[] cc) {
        len = convert(vv);
        pos = convert(pp);
        os = convert(ss);
        oe = convert(ee);
        
        for(String c : cc) {
            check();
            if(c.equals("next")) next();
            else prev();
        }
        check();
        
        return toTime();
    }
    
    public static String toTime() {
        int h = pos / 60;
        int m = pos % 60;
        String sh;
        String sm;
        if(h < 10) sh = "0" + Integer.toString(h);    
        else sh = Integer.toString(h);
        if(m < 10) sm = "0" + Integer.toString(m);
        else sm = Integer.toString(m);
        return sh + ":" + sm;
    }
    
    public static int convert(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        int h = Integer.parseInt(st.nextToken()) * 60;
        int m = Integer.parseInt(st.nextToken());
        return h + m;
    }
    
    public static void next() {
        pos += 10;
        if(pos > len) pos = len;
    }
    
    public static void prev() {
        pos -= 10;
        if(pos < 0) pos = 0;
    }
    
    public static void check() {
        if(pos >= os && pos <= oe) pos = oe;
    }
}