public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		Seg seg = new Seg(N);
		seg.init(arr, 1, 1, N);
		for (int i = 0; i < M; i++) {
			int Q = readInt();
			int L = readInt();
			int R = readInt();
			if (Q == 1) {
				if (seg.query(1, 1, N, L, R).mono) {
					sb.append("CS204\n");
				} else {
					sb.append("HSS090\n");
				}
			} else {
				seg.update(1, 1, N, L, arr[R]);
				seg.update(1, 1, N, R, arr[L]);
				int t = arr[L];
				arr[L] = arr[R];
				arr[R] = t;
			}
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

class Seg {
	public Node[] tree;

	Seg(int n) {
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		int sz = Math.toIntExact(Math.round(Math.pow(2, treeh)));
		tree = new Node[sz];
		for (int i = 0; i < sz; i++) {
			tree[i] = new Node();
		}
	}

	void init(int[] arr, int node, int start, int end) {
		if (start == end) {
			tree[node].max = tree[node].min = arr[start];
			tree[node].mono = true;
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			init(arr, next, start, mid);
			init(arr, next + 1, mid + 1, end);
			tree[node].max = Math.max(tree[next].max, tree[next + 1].max);
			tree[node].min = Math.min(tree[next].min, tree[next + 1].min);
			if (tree[next].mono && tree[next + 1].mono && tree[next].max <= tree[next + 1].min) {
				tree[node].mono = true;
			}
		}
	}

	void update(int node, int start, int end, int index, int value) {
		if (index < start || end < index)
			return;

		if (index == start && end == index) {
			tree[node].max = tree[node].min = value;
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			update(next, start, mid, index, value);
			update(next + 1, mid + 1, end, index, value);
			tree[node].max = Math.max(tree[next].max, tree[next + 1].max);
			tree[node].min = Math.min(tree[next].min, tree[next + 1].min);
			if (tree[next].mono && tree[next + 1].mono && tree[next].max <= tree[next + 1].min) {
				tree[node].mono = true;
			} else {
				tree[node].mono = false;
			}
		}
	}

	Node query(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return null;
		else if (left <= start && end <= right) {
			return tree[node];
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			Node l = query(next, start, mid, left, right);
			Node r = query(next + 1, mid + 1, end, left, right);
			if(l != null && r != null) {
				Node res = new Node(Math.min(l.min, r.min), Math.max(l.max, r.max), false);
				if (l.mono && r.mono && l.max <= r.min) {
					res.mono = true;
				}
				return res;
			} else if(l == null) {
				return r;
			} else {
				return l;
			}
		}
	}
}

class Node {
	int min;
	int max;
	boolean mono;

	public Node() {
	}

	public Node(int min, int max, boolean mono) {
		this.min = min;
		this.max = max;
		this.mono = mono;
	}
}