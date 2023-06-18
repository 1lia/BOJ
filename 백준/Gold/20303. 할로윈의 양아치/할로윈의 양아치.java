import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	public static ArrayList<Integer>[] g;
	public static void main(String[] args) throws Exception {
		Queue<Integer> q = new ArrayDeque<>();
		int N = readInt();
		int M = readInt();
		int K = readInt();
		int[] arr = new int[N+1];
		int[] dp = new int[K];
		boolean visit[] = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		
		g = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		ArrayList<B> A = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if(!visit[i]) {
				int cnt = 0;
				int cost = 0;
				q.offer(i);
				visit[i] = true;
				while(!q.isEmpty()) {
					int v = q.poll();
					cnt++;
					cost += arr[v];
					for (int j = 0; j < g[v].size(); j++) {
						if(!visit[g[v].get(j)]) {
							visit[g[v].get(j)] = true;
							q.offer(g[v].get(j));
						}	
					}
				}
				A.add(new B(cnt , cost));
			}
		}
		
		
		for (int i = 0; i < A.size(); i++) {
			B b = A.get(i);
			for (int j = K-1; j >= b.cnt; j--) {
				dp[j] = Math.max(dp[j], dp[j-b.cnt] + b.cost);
			}
		}

		System.out.println(dp[K-1]);
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

class B{
	int cnt;
	int cost;
	public B(int cnt, int cost) {
		this.cnt = cnt;
		this.cost = cost;
	}
}