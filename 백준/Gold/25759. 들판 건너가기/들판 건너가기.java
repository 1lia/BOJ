import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int[] dp = new int[101];
        Arrays.fill(dp,-1);

        for (int i = 0; i < N; i++) {
            int v = readInt();
            for (int j = 1; j < 101; j++) {
                if(dp[j] != -1){
                    int t = v - j;
                    dp[v] = Math.max(dp[v] , dp[j] + (t * t));
                }
            }
            if(dp[v] == -1)
                dp[v] = 0;
        }
        int res = 0;
        for (int i = 1; i < 101; i++) {
            res = Math.max(res , dp[i]);
        }
        System.out.println(res);
    }
    public static int readInt() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
}