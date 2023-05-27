import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static ArrayList<Integer>[] g;
	public static boolean[] visit;
	public static int[] match;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[][] map = new int[N][N];
		int[][] vmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = readInt();
			}
		}
		int x = 0;
		int y = 0;
		int cnt = 1;
		for (int z = 1; z < N+N; z++) {
			int dx = x;
			int dy = y;
			while(0 <= dx && dx < N && 0 <= dy && dy < N) {
				if(map[dx][dy] == 1) {
					vmap[dx][dy] = cnt;
				} 
				dx--;
				dy++;
			}

			if(x == N - 1) {
				y++;
			} else {
				x++;
			}
			cnt++;
		}
		g = new ArrayList[cnt];
		visit = new boolean[cnt];
		for (int i = 0; i < cnt; i++) {
			g[i] = new ArrayList<>();
		}
		x = N-1;
		y = 0;
		cnt = 1;
		for (int z = 1; z < N+N; z++) {
			int dx = x;
			int dy = y;
			
			while(0 <= dx && dx < N && 0 <= dy && dy < N) {
				if(map[dx][dy] == 1) {
					g[vmap[dx][dy]].add(cnt);
				} 
				dx++;
				dy++;
			}

			if(x == 0) {
				y++;
			} else {
				x--;
			}
			cnt++;
		}
		match = new int[cnt];
		int result = 0;
		for (int i = 1; i < visit.length; i++) {
			Arrays.fill(visit, false);
			if(dfs(i))
				result++;
		}
		System.out.println(result);
	}
	public static boolean dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if(match[next] == 0 || (!visit[match[next]] && dfs(match[next]))) {
				match[next] = v;
				return true;
			}
		}
		return false;
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