public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		UnionFind uf = new UnionFind(N);
		for (int i = 1; i <= N; i++) {
			uf.cost[i] = readInt();
		}

		for (int i = 0; i < M; i++) {
			int u = readInt();
			int v = readInt();
			uf.merge(u, v);
			sb.append(uf.query(u)).append('\n');
		}

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

class UnionFind {
	public static int[] arr;
	public static int[] cost;

	UnionFind(int n) {
		arr = new int[n + 1];
		cost = new int[n + 1];
		init();
	}

	private void init() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}

	public int query(int x) {
		x = find(x);
		return cost[x];
	}

	private int find(int x) {
		if (arr[x] == x)
			return x;

		arr[x] = find(arr[x]);
		return arr[x];
	}

	public void merge(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;
		arr[y] = x;
		cost[x] += cost[y];
		return;
	}
}