import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int K = readInt();
		int H = readInt();
		Pos[] arr = new Pos[K];
		long[] dp = new long[N + 1];
		for (int i = 0; i < K; i++) {
			arr[i] = new Pos(readInt() , readInt() , readLong());
		}
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[N] = 0;
		for (int i = K - 1; i >= 0; i--) {
			Pos p = arr[i];
			
			if(dp[p.l] != Long.MAX_VALUE && dp[p.r] != Long.MAX_VALUE) {
				long rt = Math.min(dp[p.l] , p.x + dp[p.r]);
				long lt = Math.min(dp[p.r] , p.x + dp[p.l]);
				dp[p.r] = rt;
				dp[p.l] = lt;
			} else if(dp[p.l] != Long.MAX_VALUE){
				dp[p.r] = dp[p.l];
				dp[p.l] = dp[p.l] + p.x;
			} else if(dp[p.r] != Long.MAX_VALUE) {
				dp[p.l] = dp[p.r];
				dp[p.r] = dp[p.r] + p.x;
			}
		}
		long res = 0;
		for (int i = 1; i <= N; i++) {
			if(dp[i] != Long.MAX_VALUE) {
				res = Math.max(res, dp[i]);
			}
		}
		res += dp[N] * (H - 1);
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

class Pos{
	int l;
	int r;
	long x;
	public Pos(int l, int r, long x) {
		this.l = l;
		this.r = r;
		this.x = x;
	}
}