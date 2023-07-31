import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		Prime p = new Prime();
		p.linear_sieve(N);
		int[] dp = new int[N+1];
		p.spf[1] = 1;
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if(p.spf[i] == i) {
				dp[i] = ++cnt;
			} else {
				dp[i] = dp[p.spf[i]];
			}
		}
		sb.append(cnt).append('\n');
		for (int i = 1; i <= N; i++) {
			sb.append(dp[i]).append(' ');
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

class Prime{
	public int[] spf;
	private ArrayList<Integer> prime;

	public void linear_sieve(int n) {
		spf = new int[n+1];
		prime = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if(spf[i] == 0) {
				prime.add(i);
				spf[i] = i;
			}
			
			for (int j = 0; j < prime.size(); j++) {
				int p = prime.get(j);
				if(i * prime.get(j) > n) break;
				spf[i*p] = p;
				if(i % p == 0) break;
			}
		}
	}
}