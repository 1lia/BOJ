import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		ArrayList<Integer>[] g = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		int[] visit = new int[N+1];
		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		int res = -1;
		while(!q.isEmpty()) {
			int v = q.poll();
			res++;
			if(visit[v] == 0 || visit[v] == 1) {
				for (int i = 0; i < g[v].size(); i++) {
					int nv = g[v].get(i);
					if(visit[nv] == 0 && nv != 1) {
						visit[nv] = visit[v]+1;
						q.offer(nv);
					}
					
				}
			}
		}
		
		System.out.println(res);
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