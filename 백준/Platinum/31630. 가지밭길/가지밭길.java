public class Main {
	public static void main(String[] args) throws Exception {
		int MOD = 1000000007;
		int R = readInt();
		int C = readInt();
		int K = readInt();
		boolean[][] arr = new boolean[R + 1][C + 1];
		long[][] dp = new long[R + 1][C + 1];

		for (int i = 0; i < K; i++) {
			arr[readInt()][readInt()] = true;
		}
		dp[0][0] = 1;

		for (int i = 1; i <= R; i++) {
			dp[i][0] = dp[i - 1][0];
			if(arr[i][0])
				dp[i][0] = 0;
		}
		for (int i = 1; i <= C; i++) {
			dp[0][i] = dp[0][i - 1];
			if(arr[0][i])
				dp[0][i] = 0;
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (!arr[i][j]) {
					if (dp[i - 1][j] == 0) {
						dp[i][j] = dp[i][j - 1];
					} else if (dp[i][j - 1] == 0) {
						dp[i][j] = dp[i - 1][j];
					} else if (dp[i - 1][j - 1] == 0) {
						dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
					} else if (dp[i - 1][j] != 0 && dp[i][j - 1] != 0 && dp[i - 1][j - 1] != 0) {
						dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
					}
					if (dp[i][j] < 0)
						dp[i][j] += MOD;
					dp[i][j] %= MOD;
				}
			}
		}
		System.out.println(dp[R][C]);
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