import java.util.*;

public class Main {
	public static ArrayList<Integer>[] g;
	public static boolean[] visit;
	public static boolean[] black;
	public static int[][] min;
	public static int[][] max;
	public static int[] cnt;
	public static int B;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		B = readInt();
		black = new boolean[N + 1];
		visit = new boolean[N + 1];
		cnt = new int[N + 1];
		g = new ArrayList[N + 1];
		min = new int[N + 1][B + 1];
		max = new int[N + 1][B + 1];
		int[] fin_min = new int[B + 1];
		int[] fin_max = new int[B + 1];
		Arrays.fill(fin_min, 10000);
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
			Arrays.fill(min[i], 10000);
		}
		for (int i = 0; i < B; i++) {
			black[readInt()] = true;
		}
		
		for (int i = 1; i <= N; i++) {
			if(black[i]) {
				min[i][1] = 1;
				max[i][1] = 1;
				max[i][0] = -10000000;
			} else {
				min[i][0] = 1;
				max[i][0] = 1;
			}
		}
		
		for (int i = 1; i < N; i++) {
			int u = readInt();
			int v = readInt();
			g[u].add(v);
			g[v].add(u);
		}
		
		int Q = readInt();
		int res = 0;
		count(1);
		dfs(1);
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= B; j++) {
				fin_min[j] = Math.min(fin_min[j], min[i][j]);
				fin_max[j] = Math.max(fin_max[j], max[i][j]);
			}
		}
		for (int i = 0; i < Q; i++) {
			int size = readInt();
			int cnt = readInt();
			if(fin_min[cnt] <= size && size <= fin_max[cnt]) {
				res++;
			}
		}
		System.out.println(res);	
	}
	
	public static int count(int v) {
		visit[v] = true;
		if(black[v]) {
			cnt[v]++;
		}

		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if(!visit[next]) {
				cnt[v] += count(next);
			}
		}
		return cnt[v];
	}
	
	public static void dfs(int v) {
		visit[v] = false;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);	
			if(visit[next]) {
				dfs(next);	
				for (int j = cnt[v]; j >= 0; j--) {
					int t = Math.min(j, cnt[next]);
					for (int k = 0 ; k <= t; k++) {
						min[v][j] = Math.min(min[v][j], min[next][k] + min[v][j - k]);
						max[v][j] = Math.max(max[v][j], max[next][k] + max[v][j - k]);
					}
				}
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