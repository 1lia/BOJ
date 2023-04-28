import java.util.ArrayList;

public class Main {
	public static int[][] dp;
	public static ArrayList<Integer>[] g;
	public static boolean[] visit;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		g = new ArrayList[N+1];
		dp = new int[N+1][2];
		visit = new boolean[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N; i++) {
			int U = readInt();
			int V = readInt();
			g[U].add(V);
			g[V].add(U);
		}
		dfs(1);
		System.out.println(Math.min(dp[1][0] , dp[1][1]));
	}
	public static int[] dfs(int v) {
		visit[v] = true;
		dp[v][0] = 1;
		for (int i = 0; i < g[v].size(); i++) {
			if(!visit[g[v].get(i)]) {
				int[] arr = dfs(g[v].get(i));
 				dp[v][1] += arr[0];
 				dp[v][0] += Math.min(arr[0], arr[1]);	
			}
		}
		return dp[v];
	}
	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}