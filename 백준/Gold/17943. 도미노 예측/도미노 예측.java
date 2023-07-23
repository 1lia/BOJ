public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int Q = readInt();
		long[] dp = new long[N+1];
		for (int i = 2; i <= N; i++) {
			dp[i] = readLong() ^ dp[i-1];
		}
		
		for (int i = 0; i < Q; i++) {
			int a = readInt();
			int x = readInt();
			int y = readInt();
			long res = dp[x] ^ dp[y];
			if(a == 1) 
				res ^= readLong();
			sb.append(res).append('\n');
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