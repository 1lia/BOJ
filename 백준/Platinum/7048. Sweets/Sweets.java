public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int a = readInt();
        int b = readInt();
        int[] arr = new int[N];
        int[] sum = new int[b + 1];
        int[] dp = new int[b + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }

        int t = Math.min(arr[0] , b);
        for (int i = 0; i <= t; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            sum[0] = dp[0];
            for (int j = 1; j <= b; j++) {
                sum[j] = dp[j] + sum[j - 1];
                sum[j] %= 2004;
            }

            for (int j = 1; j <= b; j++) {
                int q = Math.max(0,j - arr[i]);
                dp[j] = sum[j];
                if(q > 0){
                    dp[j] -= sum[q-1];
                    if(dp[j] < 0)
                        dp[j] += 2004;
                }
            }
        }
        int res = 0;
        for (int i = a; i <= b; i++) {
            res += dp[i];
            res %= 2004;
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