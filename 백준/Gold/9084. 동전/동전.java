public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = readInt();
		while(T-->0) {
			int N = readInt();
			int[] arr = new int[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = readInt();
			}
			int M = readInt();
			int[] dp = new int[M + 1];	
			dp[0] = 1;
			for (int i = 0; i < N; i++) {
				int cost = arr[i];
				for (int j = cost; j <= M; j++) {
					dp[j] += dp[j - cost];
				}
			}			
			sb.append(dp[M]).append('\n');
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