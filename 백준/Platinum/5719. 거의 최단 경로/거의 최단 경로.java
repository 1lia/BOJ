import java.util.*;

public class Main {
	static PriorityQueue<Pos> pq;
	static Queue<Integer> q;
	static int N , M , S , D;
	static ArrayList<Pos>[] g;
	static boolean[][] check;
	static int[] cost;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		pq = new PriorityQueue<>();
		q = new ArrayDeque<Integer>();
		while(true) {
			N = readInt();
			M = readInt();
			if(N == 0)
				break;
			S = readInt();
			D = readInt();
			q.clear();
			pq.clear();
			cost = new int[N];
			g = new ArrayList[N];
			check = new boolean[N][N];
			boolean[] visit = new boolean[N];
			ArrayList<Pos>[] back = new ArrayList[N];
			
			for (int i = 0; i < N; i++) {
				g[i] = new ArrayList<>();
				back[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				int U = readInt();
				int V = readInt();
				int P = readInt();
				g[U].add(new Pos(V , P));
				back[V].add(new Pos(U , P));
			}
			run();
			q.offer(D);
			while(!q.isEmpty()) {
				int v = q.poll();
				for (int i = 0; i < back[v].size(); i++) {
					Pos p = back[v].get(i);
					if(cost[v] - p.cost == cost[p.u]) {
						check[p.u][v] = true;
						if(!visit[p.u]) {
							visit[p.u] = true;
							q.offer(p.u);
						}
					}
				}
			}
			run();
			
			if(cost[D] != 100000000) {
				sb.append(cost[D]).append('\n');
			} else {
				sb.append(-1).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void run() {
		Arrays.fill(cost, 100000000);
		cost[S] = 0;
		pq.offer(new Pos(S,0));
		while(!pq.isEmpty()) {
			int v = pq.poll().u;
			
			for (int i = 0; i < g[v].size(); i++) {
				Pos p = g[v].get(i);
				if(cost[v] + p.cost < cost[p.u] && !check[v][p.u]) {
					cost[p.u] = cost[v] + p.cost;
					pq.offer(new Pos(p.u , cost[p.u]));
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

class Pos implements Comparable<Pos>{
	int u;
	int cost;
	public Pos(int u, int cost) {
		this.u = u;
		this.cost = cost;
	}
	@Override
	public int compareTo(Pos o) {
		return this.cost - o.cost;
	}
}