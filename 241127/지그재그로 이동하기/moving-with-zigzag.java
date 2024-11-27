import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int k = 0;
        int left = A;
        int right = A;
        int count = 0;

        if(A == B){
            System.out.println(count);
            return;
        }

        while(true){
            left = right;
            right = A + (int) Math.pow(2, k);

            if(left <= B && B <= right){
                count += Math.abs(B - left);
                break;
            }else{
                count += Math.abs(left - right);
                k++;
            }

            left = right;
            right = A - (int) Math.pow(2, k);

            if(right <= B && B <= left){
                count += Math.abs(B - right);
                break;
            }else{
                count += Math.abs(left - right);
                k++;
            }
        }

        System.out.println(count);
    }
}