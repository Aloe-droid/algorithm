import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {

        boolean[] bools = new boolean[10];
        for(int i = 2; i <= 9; i++){
            boolean flag = true;
            try {
                for(String s : expressions) {
                    String[] ss = s.split(" ");
                    int i1 = Integer.parseInt(ss[0], i);
                    int i2 = Integer.parseInt(ss[2], i);
                    if(ss[4].equals("X")) continue;
                    int i3 = Integer.parseInt(ss[4], i);
                    
                    if(ss[1].equals("+") && i1 + i2 != i3) {
                        flag = false;
                        break;
                    }else if(ss[1].equals("-") && i1 - i2 != i3) {
                        flag = false;
                        break;
                    }
                }
            }catch(Exception e) {
                flag = false;
            }
            
            if(flag) bools[i] = true;
        }
        
        List<String> list = new ArrayList<>();
        for(int i = 0; i < expressions.length; i++) {
            String s = expressions[i];
            if(s.charAt(s.length() - 1) == 'X') list.add(s);
        }
     
        for(int i = 2; i <= 9; i++) {
            if(!bools[i]) continue;
            for(int j = 0; j < list.size(); j++) {
                String s = list.get(j);
                String[] ss = s.split(" ");
                int a = Integer.parseInt(ss[0], i);
                int b = Integer.parseInt(ss[2], i);
                int c;
                if(ss[1].equals("+")) c = a + b;
                else c = a - b;
                
                String x = Integer.toString(c, i);
                if(!ss[4].equals("X") && !ss[4].equals(x)) x = "?";
                String newS = ss[0] + " " + ss[1] + " " + ss[2] + " " + ss[3] + " " + x;
                list.set(j, newS);
            }
        }
        return list.toArray(new String[0]);
    }
}