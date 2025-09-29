import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        if(t == 0){
            System.out.println("! " + (N >> 1));
            while(N > 0){
                N-=2;
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                System.out.println(b + " " + a);
                System.out.flush();
            }
        } else{
            System.out.println("! " + (((N - 2) >> 1) + 2));
            System.out.println("1 1");
            while(N > 0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                N-=2;
                if(N == 0)
                    break;
                System.out.println(b + " " + a);
                System.out.flush();
            }
        }
    }
}