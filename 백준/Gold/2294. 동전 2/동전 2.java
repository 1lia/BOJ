import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		int n = readInt();
		int k = readInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = readInt();
		}
		int[] dp = new int[k + 1];
		Arrays.fill(dp, 100000);
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			int cost = arr[i];
			for (int j = cost; j <= k; j++) {
				dp[j] = Math.min(dp[j - cost] + 1, dp[j]);
			}
		}
		if(dp[k] == 100000)
			System.out.println(-1);
		else
			System.out.println(dp[k]);
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