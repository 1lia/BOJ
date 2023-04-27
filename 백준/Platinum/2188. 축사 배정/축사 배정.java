import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static boolean[] visit;
	public static int[] match;
	public static ArrayList<Integer>[] g;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		match = new int[M+1];
		visit = new boolean[N+1];
		g = new ArrayList[N+1];  
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
			int s = readInt();
			for (int j = 0; j < s; j++) {
				g[i].add(readInt());
			}
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visit, false);
			if(dfs(i))
				cnt++;
		}
		System.out.println(cnt);
	}
	
	public static boolean dfs(int v) {
		visit[v] = true; 
		for (int i = 0; i < g[v].size(); i++) {
			int nxt = g[v].get(i);
			if(match[nxt] == 0 || (!visit[match[nxt]] && dfs(match[nxt]))) {
				match[nxt] = v;
				return true;
			}
		}
		return false;
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