import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		LCA lca = new LCA(N);
		for (int i = 1; i < N; i++) {
			lca.putEdge(readInt(), readInt());
		}
		lca.run();
		int Q = readInt();
		for (int i = 0; i < Q; i++) {
			int r = readInt();
			int u = readInt();
			int v = readInt();

			int a = lca.query(r, u);
			int b = lca.query(r, v);
			if (a == b) {
				sb.append(lca.query(u, v)).append('\n');
			} else if (a != r && b != r) {
				int t = lca.query(u, v);
				if(a == t) {
					sb.append(b).append('\n');
				} else {
					sb.append(a).append('\n');
				}
			} else {
				sb.append(r).append('\n');
			}
		}

		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
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

class LCA {
	int N;
	boolean[] visited;
	int[] depth;
	int[][] parent;
	int size;
	ArrayList<Integer>[] tree;

	public LCA(int n) {
		N = n;
		size = (int) Math.ceil(Math.log(N + 1) / Math.log(2)) + 1;
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[N + 1][size];
		tree = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
	}

	public void run() {
		dfs(1, 1, 1);
		for (int i = 1; i < size; i++) {
			for (int j = 1; j <= N; j++) {
				int t = parent[j][i - 1];
				parent[j][i] = parent[t][i - 1];
			}
		}
	}

	public void putEdge(int a, int b) {
		tree[a].add(b);
		tree[b].add(a);
	}

	private void dfs(int up, int node, int dep) {
		if (visited[node])
			return;
		visited[node] = true;
		depth[node] = dep;
		parent[node][0] = up;
		for (int i = 0; i < tree[node].size(); i++) {
			dfs(node, tree[node].get(i), dep + 1);
		}
	}

	public int query(int a, int b) {
		if (depth[a] < depth[b]) {
			int t = a;
			a = b;
			b = t;
		}
		for (int i = size - 1; i >= 0; i--) {
			if (depth[a] - depth[b] >= (1 << i)) {
				a = parent[a][i];
			}
		}

		if (a == b)
			return a;

		for (int i = size - 1; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}

		return parent[a][0];
	}
}