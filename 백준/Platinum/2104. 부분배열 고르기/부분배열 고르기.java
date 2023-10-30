import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Stack<Integer> s = new Stack<>();

		int N = readInt();
		long[] arr = new long[N + 1];
		long[] dp = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = arr[i] = readInt();
			dp[i] += dp[i-1];
		}
		s.clear();
		long res = 0;
		for (int i = 1; i <= N; i++) {
			while (!s.isEmpty() && arr[s.peek()] > arr[i]) {
				int a = s.pop();
				if (s.isEmpty()) {
					res = Math.max(res, arr[a] * dp[i-1]);
				} else {
					res = Math.max(res, arr[a] * (dp[i-1] - dp[s.peek()]));
				}
			}
			
			s.add(i);
		}
		while (!s.isEmpty()) {
			int a = s.pop();
			if (s.isEmpty()) {
				res = Math.max(res, arr[a] * dp[N]);
			} else {
				res = Math.max(res, arr[a] * (dp[N] - dp[s.peek()]));
			}
		}
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
}