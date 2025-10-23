public class Main {
    public static void main(String[] args) throws Exception{
        long MOD = 1_000_000_007;
        StringBuilder sb = new StringBuilder();
        long[] dp = new long[3500];
        dp[1] = 3;
        dp[2] = 13;
        dp[3] = 57;
        for (int i = 4; i <= 3400; i++) {
            dp[i] = (5 * dp[i - 1] - 3 * dp[i - 2] + dp[i - 3] + (MOD << 2)) % MOD;
            if(dp[i] < 0){
                System.out.println(dp[i]);
            }
        }

        int T = readInt();
        while(T-->0){
            int N = readInt();
            if(N % 3 == 0){
                sb.append(dp[N/3]);
            } else{
                sb.append(0);
            }
            sb.append('\n');
        }
        System.out.println(sb);
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