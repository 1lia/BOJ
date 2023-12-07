import java.util.*;

public class Main {
	public static int[] dp;
	public static ArrayList<Integer>[] g;
	public static boolean[] visit;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		g = new ArrayList[N+1];
		visit = new boolean[N+1];
		dp = new int[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>(); 
		}
		
		for (int i = 1; i < N; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		dfs(1);	
		if(dp[1] == 0) {
			System.out.println("Bob");
		} else {
			System.out.println("Alice");
		}
	}
	
	private static int dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if(!visit[next]) {
				dp[v] += dfs(next);
			}
		}
		if(dp[v] == 0) {
			return dp[v] = 1;
		}
		return --dp[v];
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