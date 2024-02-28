import java.util.*;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[][] dp;
	public static int[] arr;
	public static void main(String[] args) throws Exception {	
		int N = readInt();
		arr = new int[N + 1];
		dp = new int[N + 1][3];
		g = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
			
		}
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			int v = readInt();
			int u = readInt();
			g[v].add(u);
			g[u].add(v);
		}
		dfs(1,1);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	public static void dfs(int v , int p) {
		dp[v][0] = arr[v];
		boolean flag = false;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if(next != p) {
				dfs(next , v);
				dp[v][0] += Math.max(dp[next][1] , dp[next][2]);
				dp[v][1] += Math.max(dp[next][0] , dp[next][1]);
				dp[v][2] += Math.max(dp[next][0] , dp[next][1]);
				if(dp[next][0] >= dp[next][1]) {
					flag = true;
				}
				min = Math.min(min, dp[next][1] - dp[next][0]);
			}
		}
		if(!flag && min != Integer.MAX_VALUE) {
			dp[v][1] -= min;
		}
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