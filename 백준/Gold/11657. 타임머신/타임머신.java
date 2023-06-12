import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		Spfa spfa = new Spfa(N);
		for (int j = 0; j < M; j++) {
			int s = readInt();
			int e = readInt();
			long t = readLong();
			spfa.putEdge(s, e, t);
		}
		spfa.print(1);
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
	
	public static long readLong() throws Exception {
		long val = 0;
		long c = System.in.read();
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

class Spfa {
	private ArrayList<Node>[] g;
	private long[] dist;
	private int[] cycle;
	private boolean[] visited;
	private int N;

	public Spfa(int N) {
		init(N);
	}

	private void init(int N) {
		this.N = N;
		g = new ArrayList[N + 1];
		dist = new long[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		cycle = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
	}

	public boolean run(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(v);
		cycle[v]++;
		visited[v] = true;
		dist[v] = 0;
		while (!q.isEmpty()) {
			v = q.poll();
			visited[v] = false;
			for (int i = 0; i < g[v].size(); i++) {
				Node node = g[v].get(i);
				if (node.cost + dist[v] < dist[node.v]) {
					dist[node.v] = node.cost + dist[v];
					if (!visited[node.v]) {
						q.offer(node.v);
						cycle[node.v]++;
						visited[node.v] = true;
					}
				}
			}
			if (cycle[v] > N) {
				return false;
			}
		}
		return true;
	}

	public void print(int v) {
		if (run(v)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				if (dist[i] == Integer.MAX_VALUE)
					sb.append(-1).append('\n');
				else
					sb.append(dist[i]).append('\n');
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
		
	}

	public void putEdge(int s, int e, long c) {
		g[s].add(new Node(e, c));
	}

	class Node {
		int v;
		long cost;

		public Node(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}