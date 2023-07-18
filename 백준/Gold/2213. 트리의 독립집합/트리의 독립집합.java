import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[][] dp;
	public static int[] cost;
	public static boolean[] visit;
	public static ArrayList<Integer> res;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		cost = new int[N+1];
		visit = new boolean[N+1];
		g = new ArrayList[N+1];
		res = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		dp = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			cost[i] = readInt();
		}
		
		for (int i = 1; i < N; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		dfs(1);
		sb.append(Math.max(dp[1][0], dp[1][1])).append('\n');
		Arrays.fill(visit, false);
		run(1 , false);
		Collections.sort(res);
		for (int i = 0; i < res.size(); i++) {
			sb.append(res.get(i)).append(' ');
		}
		System.out.println(sb);
	}
	
	public static int[] dfs(int v) {
		visit[v] = true;
		dp[v][0] += cost[v];
		int y = 0;
		int n = 0;
		for (int i = 0; i < g[v].size(); i++) {
			if(!visit[g[v].get(i)]) {
				int[] t = dfs(g[v].get(i));
				y += t[1];
				n += Math.max(t[0], t[1]);
			}
		}
		dp[v][0] += y;
		dp[v][1] += n;
		return dp[v];
	}
	
	public static void run(int v , boolean check) {
		visit[v] = true;
		if(dp[v][0] > dp[v][1] && !check) {
			check = true;
			res.add(v);
		} else {
			check = false;
		}
		for (int i = 0; i < g[v].size(); i++) {
			if(!visit[g[v].get(i)]) {
				run(g[v].get(i) , check);
			}
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