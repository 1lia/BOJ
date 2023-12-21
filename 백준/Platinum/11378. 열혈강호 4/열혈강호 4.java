import java.util.*;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] visit;
	public static int[] match;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int K = readInt();
		g = new ArrayList[N + 1];
		visit = new int[N + 1];
		match = new int[M + 1];
		
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			int cnt = readInt();
			for (int j = 0; j < cnt; j++) {
				g[i].add(readInt());
			}
		}
		int res = 0;
		int cnt = N + 1;
		for (int i = 1; i <= N; i++) {
			if(dfs(i , i)) {
				res++;
			}
		}
		int z = 1;
		while(z <= N && K != 0) {
			if(dfs(z , cnt++)) {
				K--;
				res++;
			} else {
				z++;
			}
		}
		System.out.println(res);	
	}
	
	public static boolean dfs(int v , int state) {
		visit[v] = state;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			int nv = match[next];
			if(nv == 0 || visit[nv] != state && dfs(nv , state)) {
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