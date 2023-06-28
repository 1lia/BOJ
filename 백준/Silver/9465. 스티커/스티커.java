public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = readInt();
		for (int z = 0; z < T; z++) {
			int N = readInt();
			int[][] dp = new int[2][N];
			int[][] arr = new int[2][N];
			for (int i = 0; i < N; i++) {
				arr[0][i] = readInt();
			}
			for (int i = 0; i < N; i++) {
				arr[1][i] = readInt();
			}
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			if(N != 1) {
				dp[0][1] = arr[0][1] + arr[1][0];
				dp[1][1] = arr[1][1] + arr[0][0];
			}

			for (int i = 2; i < N; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + arr[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + arr[1][i];
			}
			
			sb.append(Math.max(dp[0][N-1], dp[1][N-1])).append('\n');
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