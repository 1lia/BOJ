public class Main {
	public static long[][][][] dp;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int a = readInt();
		int b = readInt();
		int c = readInt();
		if(a+b+c < N) {
			System.out.println(0);
			return;
		}
		dp = new long[N + 1][a + 1][b + 1][c + 1];
		for (int i = 0; i <= N; i++)
		for (int j = 0; j <= a; j++)
		for (int k = 0; k <= b; k++)
		for (int l = 0; l <= c; l++)
			dp[i][j][k][l] = -1;
		dp[0][0][0][0] = 1;
		System.out.println(dfs(N, a, b, c));
	}

	public static long dfs(int n, int a, int b, int c) {
		if (n < 0 || a < 0 || b < 0 || c < 0) {
			return 0;
		}
		
		if (dp[n][a][b][c] != -1) {
			return dp[n][a][b][c];
		}
		
		long t = 0;
		t += dfs(n - 1, a - 1, b, c);
		t += dfs(n - 1, a, b - 1, c);
		t += dfs(n - 1, a, b, c - 1);
		t += dfs(n - 1, a - 1, b - 1, c);
		t += dfs(n - 1, a - 1, b, c - 1);
		t += dfs(n - 1, a, b - 1, c - 1);
		t += dfs(n - 1, a - 1, b - 1, c - 1);
        t %= 1000000007;
		return dp[n][a][b][c] = t; 
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