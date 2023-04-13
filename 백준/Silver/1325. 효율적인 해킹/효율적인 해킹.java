import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static ArrayList<Integer>[] g;
	public static boolean[] visit;
	public static StringBuilder sb = new StringBuilder();
	public static int[] dp;

	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();

		g = new ArrayList[N + 1];
		visit = new boolean[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			g[b].add(a);
		}
		int cnt = 0;
		int result = 0;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visit, false);
			cnt = dfs(i);
			if (result == cnt) {
				sb.append(i).append(' ');
			} else if (result < cnt) {
				sb = new StringBuilder();
				sb.append(i).append(' ');
				result = cnt;
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	public static int dfs(int v) {
//		if (dp[v] != 0) {
//			return dp[v];
//		}
		int cnt = 1;
		visit[v] = true;
//		dp[v]++;

		for (int i = 0; i < g[v].size(); i++) {
			if (!visit[g[v].get(i)]) {
				cnt += dfs(g[v].get(i));
			}
		}
		return cnt;
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
