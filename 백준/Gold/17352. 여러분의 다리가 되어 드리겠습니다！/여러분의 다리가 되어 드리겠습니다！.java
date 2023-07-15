public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		UnionFind uf = new UnionFind(N);
		
		for (int i = 2; i < N; i++) {
			uf.merge(readInt(), readInt());
		}
		
		for (int i = 2; i <= N; i++) {
			if(!uf.Union(1, i)) {
				System.out.println(1 + " " + i);
				break;
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

class UnionFind {
	public static int[] arr;
	UnionFind(int n) {
		arr = new int[n + 1];
		init();
	}

	private void init() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
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
		return;
	}

	public boolean Union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return true;
		return false;
	}
}