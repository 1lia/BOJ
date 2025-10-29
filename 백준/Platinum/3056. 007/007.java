public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        double[][] arr = new double[N][N];
        double[] dp = new double[1 << N];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = readInt();
                arr[i][j] /= 100;
            }
        }

        for (int i = 0; i < 1 << N; i++) {
            int bit = Integer.bitCount(i);
            for (int j = 0; j < N; j++) {
                if((i & (1<<j)) == 0){
                    int next = i | (1<<j);
                    dp[next] = Math.max(dp[next] , dp[i] * arr[bit][j]);
                }
            }
        }
        System.out.println(dp[(1 << N) - 1] * 100);
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
