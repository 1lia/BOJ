import java.util.Arrays;

public class Main {
	public static long[][] dp;
	public static int[] arr;
	public static int N;
	public static void main(String[] args) throws Exception {
		N = readInt();
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		
		dp = new long[N][21];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		long res = dfs(1,arr[1]);
		System.out.println(res);
	}
	
	public static long dfs(int cnt , int n) {
		if(n < 0 || 20 < n)
			return 0;
		
		if(dp[cnt][n] != -1)
			return dp[cnt][n];
		
		if(cnt == N - 1) {
			if(n == arr[N])
				return dp[cnt][n] = 1;
			return dp[cnt][n] = 0;
		}
		
		dp[cnt][n] = 0;
		dp[cnt][n] += dfs(cnt+1 , n + arr[cnt+1]);
		dp[cnt][n] += dfs(cnt+1 , n - arr[cnt+1]);
		return dp[cnt][n];
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