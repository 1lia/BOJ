public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int W = readInt();
		int[][] dp = new int[N+1][W + 1];
		for (int i = 1; i <= N; i++) {
			int t = readInt() - 1;
			dp[i][0] = dp[i-1][0];
			for (int j = 1; j <= W; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]);
			}
			for (int j = t; j <= W; j+=2) {
				dp[i][j]++;
			}
		}
		int res = 0;
		for (int i = 0; i <= W; i++) {
			res = Math.max(res, dp[N][i]);
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