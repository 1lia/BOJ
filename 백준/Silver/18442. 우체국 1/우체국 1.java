import java.util.*;

public class Main {
	public static long[][] arr;
	public static long[][] sum;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int V = (int) readLong();
		int P = (int) readLong();
		long L = readLong();
		arr = new long[V + 1][V + 1];
		sum = new long[V + 1][V + 1];
		if(V == 1) {
			System.out.println(0);
			System.out.println(readLong());
			return;
		}
		
		long[][][] dp = new long[V + 1][V + 1][P + 1];
		for (int i = 1; i <= V; i++) {
			sum[1][i] = arr[1][i] = readLong();
			sum[1][i] += sum[1][i-1];
		}
		for (int i = 2; i <= V; i++) {
			for (int j = 1; j < V; j++) {
				sum[i][j] = arr[i][j] = arr[i-1][j+1];
				sum[i][j] += sum[i][j-1];
			}
			sum[i][V] = arr[i][V] = arr[i-1][1] + L;
			sum[i][V] += sum[i][V-1];
		}
		for (int i = 0; i <= V; i++) {
			for (int j = 0; j <= V; j++) {
				Arrays.fill(dp[i][j] , 10000000000000L);
				dp[i][j][0] = 0;
			}
		}
		int v = 0;
		long res = Long.MAX_VALUE;
		for (int z = 1; z <= V; z++) {
			for (int i = 1; i <= V; i++) {
				dp[z][i][1] = cost(z,1,i);
				for (int j = 2; j <= P; j++) { //그룹 개수
					for (int k = 1; k < i; k++) {
						dp[z][i][j] = Math.min(dp[z][i][j] , dp[z][k][j-1] + cost(z , k + 1, i));	
					}
				}
			}
			if(res > dp[z][V][P]) {
				res = dp[z][V][P];
				v = z;
			}
		}
		sb.append(res).append('\n');
		ArrayList<Long> solve = new ArrayList<>();
		int r = V;
		int p = P;
		for (int i = V; i > 0 && p != 0; i--) {
			if(dp[v][r][p] == dp[v][i - 1][p - 1] + cost(v,i,r)) {
				solve.add(arr[v][(i + r) >> 1]);
				p--;
				r = i-1;
			}
		}
		for (int i = 0; i < P; i++) {
			if(solve.get(i) >= L) {
				solve.set(i, solve.get(i) - L);
			}
		}
		Collections.sort(solve);
		for (int i = 0; i < P; i++) {
			sb.append(solve.get(i)).append(' ');
		}
		System.out.println(sb);
	}
	
	public static long cost(int v ,int l , int r) {
		int m = (l + r) >> 1;
		return arr[v][m] * (m + m - l - r + 1) + sum[v][r] + sum[v][l - 1] - sum[v][m] - sum[v][m];
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