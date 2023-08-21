public class Main {
    public static void main(String[] args) throws Exception {
        int N = (int) readLong();
        long[] arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = readLong();
        }
        if(N == 1){
            System.out.println(arr[1]);
            return;
        } else if(N == 2){
            System.out.println(arr[1] ^ arr[2]);
            return;
        } else if(N == 3){
            System.out.println(Math.max((arr[1] ^ arr[2]) + arr[3] , Math.max(arr[1] + (arr[2] ^ arr[3]) , arr[1] ^ arr[2] ^ arr[3])));
            return;
        }
        long[][] dp = new long[N+1][5];
        dp[1][1] = arr[1];
        dp[2][2] = arr[1] ^ arr[2];
        dp[3][1] = (arr[1] ^ arr[2]) + arr[3];
        dp[3][2] = arr[1] + (arr[2] ^ arr[3]);
        dp[3][3] = arr[1] ^ arr[2] ^ arr[3];

        for (int i = 4; i <= N; i++) {
            long t = arr[i];
            dp[i][1] = Math.max(dp[i-1][4] + t , Math.max(dp[i-1][3] + t , dp[i-1][2] + t));
            t ^= arr[i-1];
            dp[i][2] = Math.max(dp[i-2][4] + t , Math.max(dp[i-2][3] + t , dp[i-2][1] + t));
            t ^= arr[i-2];
            dp[i][3] = Math.max(dp[i-3][4] + t , Math.max(dp[i-3][2] + t , dp[i-3][1] + t));
            t ^= arr[i-3];
            dp[i][4] = Math.max(dp[i-4][4] + t , Math.max(dp[i-4][3] + t , dp[i-4][1] + t));
        }
        System.out.println(Math.max(dp[N][1] , Math.max(dp[N][2] , Math.max(dp[N][3] , dp[N][4]))));

    }
    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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