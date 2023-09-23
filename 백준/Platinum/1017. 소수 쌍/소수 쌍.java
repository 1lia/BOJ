import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static boolean[] visit;
	public static int[] match;
	public static ArrayList<Integer>[] g;
	public static void main(String[] args) throws Exception {
		boolean[] prime = new boolean[2001]; //flase = 소수
		for (int i = 2; i < 999; i++) {
			if(!prime[i]) {
				int t = (i << 1);
				while(t <= 2000) {
					prime[t] = true;
					t += i;
				}
			}
		}
		int N = readInt();
		visit = new boolean[N+1];
		match = new int[N+1];
		g = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		ArrayList<Integer> res = new ArrayList<>();
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if(!prime[arr[i] + arr[j]]) {
					g[i].add(j);
					g[j].add(i);
				}
			}
		}	

		for (int idx : g[1]) {
			int cnt = 0;
			Arrays.fill(match, 0);
			for (int j = 2; j <= N; j++) {
				Arrays.fill(visit, false);
				if(j != idx && dfs(j , idx)) {
					cnt++;
				}
			}
			if(cnt == (N - 2)) {
				res.add(arr[idx]);
			}
		}
		
		if(res.size() ==0) {
			System.out.println(-1);
		} else {
			Collections.sort(res);
			StringBuilder sb = new StringBuilder();
			for (int i : res) {
				sb.append(i).append(' ');
			}
			System.out.println(sb);
		}
	}
	
	public static boolean dfs(int v , int idx) {
		visit[v] = true;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if(next != idx && next != 1 && (match[next] == 0 || (!visit[match[next]] && dfs(match[next] , idx)))) {
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