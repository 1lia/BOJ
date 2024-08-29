import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[][] dp = new int[1001][2004];
        for (int i = 0; i <= 1000; i++) {
            Arrays.fill(dp[i] , 10000);
        }

        for (int i = 0; i < N; i++) {
            int T = readInt();
            int l = readInt() + 1001;
            int r = readInt() + 1001;
            for (int j = l + 1; j < r; j++) {
                dp[T][j] = 100000000;
            }
        }

        dp[0][1001] = 0;
        int l = 1001;
        int r = 1001;
        for (int i = 1; i <= 1000; i++) {
            l--;
            r++;
            for (int j = l; j <= r; j++) {
                if(dp[i][j] != 100000000){
                    dp[i][j] = Math.min(dp[i-1][j - 1] + 1 , Math.min(dp[i-1][j + 1] + 1 , dp[i-1][j]));
                }
            }
        }
        int res = 1001;
        for (int i = 0; i <= 2000; i++) {
            res = Math.min(res , dp[1000][i]);
        }
        if(res > 1000)
            res = -1;
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