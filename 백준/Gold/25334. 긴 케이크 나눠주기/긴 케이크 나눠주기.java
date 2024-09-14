import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            String str = br.readLine();
            ArrayList<Long> arr = new ArrayList();
            long all = 0;
            
            for (int i = 0; i < n; i++) {
                char c = str.charAt(i);
                if(c == '1'){
                    all++;
                }
            }
            if(all == 0){
                sb.append(binomial_fermat(n - 1 , k - 1)).append('\n');
                continue;
            }

            if(all % k != 0){
                sb.append(0).append('\n');
                continue;
            }

            long t = all / k;
            long cnt = 0;
            long res = 1;
            all = 0;
            for (int i = 0; i < n; i++) {
                char c = str.charAt(i);
                if(c == '1'){
                    all++;
                    if(all != 1 && (all - 1) % t == 0){
                        res *= (cnt + 1);
                        res %= 1000000007;
                    }
                    cnt = 0;
                } else{
                    cnt++;
                }
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);
    }

    public static long binomial_fermat(long n , long k){
        long MOD = 1000000007L;
        long A = 1;
        long B = 1;

        for (long i = 2; i <= n; i++) {
            A *= i;
            A %= MOD;
        }

        for (long i = 2; i <= k; i++) {
            B *= i;
            B %= MOD;
        }

        for(long i = 2; i + k <= n; i++){
            B *= i;
            B %= MOD;
        }

        long B2 = 1;
        long p = MOD - 2;
        while(p > 0){
            if (p % 2 != 0){
                B2 *= B;
                B2 %= MOD;
            }

            B *= B;
            B %= MOD;
            p /= 2;
        }
        return (A * B2) % MOD;
    }
}