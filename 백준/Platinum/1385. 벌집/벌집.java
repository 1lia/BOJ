import java.util.*;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] visit;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		init();
		int s = readInt();
		int e = readInt();
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(s);
		visit[s] = -1;
		ArrayList<Integer> res = new ArrayList<>();
		while(!q.isEmpty()) {
			int v = q.poll();
			if(v == e)
				break;
			for (int u : g[v]) {
				if(visit[u] == 0) {
					visit[u] = v;
					q.offer(u);
				}
			}
		}
		while(visit[e] != -1) {
			res.add(e);
			e = visit[e];
		}
		res.add(s);
		int l = res.size();
		for (int i = l - 1; i >= 0; i--) {
			sb.append(res.get(i)).append(' ');
		}
		System.out.println(sb);
	}
	
	public static void init() {
		int size = 6;
		int t = 2;
		int check = 9;
		int cnt = 2;
		int fin = 8;
		visit = new int[1000001];
		g = new ArrayList[1000001];
		for (int i = 0; i <= 1000000; i++) {
			g[i] = new ArrayList<>();
		}
		g[1].add(2); g[2].add(1); g[2].add(3); g[2].add(7);
		for (int i = 3; i <= 6; i++) {
			g[1].add(i);
			g[i].add(1);
			g[i].add(i - 1);
			g[i].add(i + 1);
		}
		g[1].add(7); g[7].add(1); g[7].add(2); g[7].add(6);
		
		for (int i = 8; i <= 1000000; i++) {
			g[t].add(i);
			g[i].add(t);
			g[i-1].add(i);
			g[i].add(i-1);
			
			if(check == i) {
				if(--size == 0) {
					size = 6;
					cnt++;
					t++;
					g[t].add(i);
					g[i].add(t);
				} 
				check += cnt;
			} else if(fin == i) {
				fin += (6 * cnt);
			} else {
				t++;
				g[t].add(i);
				g[i].add(t);
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