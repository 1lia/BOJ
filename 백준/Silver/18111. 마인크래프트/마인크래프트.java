public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt() * readInt();
		int B = readInt();
		int[] dp = new int[257];
		int sum = B;
		int min = 300;
		int max = 0;

		for (int i = 0; i < N; i++) {
			int t = readInt();
			dp[t]++;
			sum += t;
			if (max < t)
				max = t;
			if (min > t)
				min = t;

		}
		max = Math.min(max, sum / N);
		int cost = 0;
		for (int i = min + 1; i < 257; i++) {
			cost += dp[i] * 2 * (i - min);
		}
		int result = cost;
		int hi = min;
		for (int i = min + 1; i <= max; i++) {
			cost -= (N-dp[i-1]) * 2;
			cost += dp[i-1];
			dp[i] += dp[i-1];
			if (cost <= result) {
				result = cost;
				hi = i;
			}
		}
		System.out.println(result + " " + hi);
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