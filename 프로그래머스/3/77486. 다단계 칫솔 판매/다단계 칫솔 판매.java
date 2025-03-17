import java.util.*;

class Solution {
    static Map<String, Seller> hm;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        init(enroll, referral, seller, amount);
        return print(enroll);
    }
    
    public static void init(String[] enroll, String[] referral, String[] seller, int[] amount) {
        hm = new HashMap<>();
        hm.put("-", new Seller("-"));
        
        for(int i = 0; i < enroll.length; i++) {
            String er = enroll[i];
            String rf = referral[i];
            Seller sr = new Seller(er);
            sr.ref = hm.get(rf);
            hm.put(er, sr);
        }
        
        for(int i = 0; i < seller.length; i++) hm.get(seller[i]).setPrice(amount[i] * 100);
    }
    
    public static int[] print(String[] enroll) {
        int[] price = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) price[i] = hm.get(enroll[i]).total;
        return price;
    }
}

class Seller {
    String name;
    Seller ref;
    int total;
    
    public Seller(String name) {
        this.name = name;
        total = 0;
    }
    
    public void setPrice(int price) {
        int tax = price / 10;
        total += price - tax;
        if(ref != null && tax != 0) ref.setPrice(tax);
    }
}