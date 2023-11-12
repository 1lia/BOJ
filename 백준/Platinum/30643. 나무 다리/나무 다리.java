public class Main {
	public static void main(String[] args) throws Exception {
		long MOD = 1000000007;
		int W = readInt();
		int L = readInt();
		if(L == 2) {
			System.out.println(1);
			return;
		}
		long sum = 0;
		for (int z = 0; z < 3; z++) {
			long[][] dp = new long[W][L];
			for (int i = 0; i + 1< L; i++) {
				dp[0][i] = 1;
			}
			
			if(L == 1) {
				
			} else if(L == 2) {
				if(z == 1) {
					sum -= 2;
				} else {
					sum += 1;
				}
			} else {
				for (int i = 1; i < W; i++) {
					dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;
					dp[i][L-2] = (dp[i-1][L-2] + dp[i-1][L-3]) % MOD;
					for (int j = 1; j + 2 < L; j++) {
						dp[i][j] = (dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1]) % MOD;
					}
				}

				if(z == 0) {
					for (int i = 0; i < L; i++) {
						sum += dp[W-1][i];
					}
				} else if (z == 1){
					for (int i = 0; i < L; i++) {
						sum -= (dp[W-1][i] << 1);
					}
				} else {
					for (int i = 0; i < L; i++) {
						sum += dp[W-1][i];
					}
				}
				sum %= MOD;
			}
			L--;
		}
		if(sum < 0) {
			sum += MOD;
		}
		System.out.println(sum % MOD);
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