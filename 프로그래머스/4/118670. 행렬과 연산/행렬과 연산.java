import java.util.*;

class Solution {
    static int r, c;
    static Deque<Integer> left, right;
    static Deque<Deque<Integer>> row;
    
    public int[][] solution(int[][] rc, String[] operations) {
        r = rc.length;
        c = rc[0].length;
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        row = new ArrayDeque<>();
        int[][] ans = new int[r][c];
        
        for(int i = 0; i < r; i ++){
            Deque<Integer> dq = new ArrayDeque<>();
            left.add(rc[i][0]);
            right.add(rc[i][c - 1]);
            for(int j = 1; j < c - 1; j++){
                dq.add(rc[i][j]);
            }
            row.add(dq);
        }
        
        for(String s : operations){
            if(s.equals("ShiftRow")) sft();
            else rot();
        }
        
        for(int i = 0; i < r; i++){
            ans[i][0] = left.poll();
            ans[i][c - 1] = right.poll();
            
            Deque<Integer> dq = row.poll();
            for(int j = 1; j < c - 1; j++){
                ans[i][j] = dq.poll();
            }
        }
        
        return ans;
    }
    
    public static void sft(){
        row.addFirst(row.pollLast());
        left.addFirst(left.pollLast());
        right.addFirst(right.pollLast());
    }
    
    public static void rot(){
        row.getFirst().addFirst(left.pollFirst());
        right.addFirst(row.getFirst().pollLast());
        row.getLast().addLast(right.pollLast());
        left.addLast(row.getLast().pollFirst());
    }
}