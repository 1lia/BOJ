import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        long[] arr = new long[86401];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            int s = Integer.parseInt(s1.substring(0 , 2)) * 3600 + Integer.parseInt(s1.substring(3 , 5)) * 60 + Integer.parseInt(s1.substring(6 , 8)) + 1;
            int e = Integer.parseInt(s2.substring(0 , 2)) * 3600 + Integer.parseInt(s2.substring(3 , 5)) * 60 + Integer.parseInt(s2.substring(6 , 8)) + 1;

            if(q == 1){
                arr[s]++;
                arr[e]--;
            } else if(!flag){
                flag = true;
                for (int j = 1; j <= 86400; j++) {
                    arr[j] += arr[j - 1];
                }

                for (int j = 1; j <= 86400; j++) {
                    arr[j] += arr[j - 1];
                }
            }
            if(q == 2){
                sb.append(arr[e - 1] - arr[s - 1]).append('\n');
            }
        }
        System.out.println(sb);
    }
}