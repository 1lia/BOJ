public class Main {
    public static void main(String[] args) throws Exception {
        int N = (int) readLong();
        int M = (int) readLong();
        long[] arr = new long[N+1];
        long[] bos = new long[N+1];
        long[] sum = new long[N+1];
        long[][] dp = new long[N+1][2];

        for (int i = 1; i <= N; i++) {
            arr[i] = readLong();
        }

        for (int i = 1; i <= N; i++) {
            bos[i] = readLong();
        }

        if(N < M){
            long res = 0;
            for (int i = 1; i <= N; i++) {
                res += arr[i];
            }
            System.out.println(res);
        } else {
            for (int i = 1; i < M; i++) {
                sum[i] = arr[i] + sum[i - 1];
            }

            for (int i = M; i <= N; i++) {
                sum[i] = sum[i - 1] + arr[i] - arr[i - M];
            }

            for (int i = 1; i < M; i++) {
                dp[i][0] = Math.max(dp[i - 1][0] + arr[i], dp[i - 1][1] + arr[i]);
                dp[i][1] = Math.max(dp[i - 1][0] - arr[i], dp[i - 1][1] - arr[i]);
            }

            for (int i = M; i <= N; i++) {
                dp[i][0] = Math.max(dp[i - 1][0] + arr[i], dp[i - M][1] + sum[i] + bos[i]);
                dp[i][1] = Math.max(dp[i - 1][0] - arr[i], Math.max(dp[i - M][1] + sum[i] + bos[i], dp[i - 1][1] - arr[i]));
            }
            System.out.println(Math.max(dp[N][1], dp[N][0]));
        }
    }
    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
        while(c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if(flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if(flag)
            return -val;
        return val;
    }
}