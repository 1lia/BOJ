public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		Seg seg = new Seg(N);
		for (int i = 1; i <= N; i++) {
			seg.arr[i] = readLong();
		}
		seg.initLazySeg(1, 1, N);
		int Q = readInt();
		while (Q-- > 0) {
			int t = readInt();
			if (t == 1) {
				int idx = seg.findidx(readInt());
				seg.updateIdxTree(idx, -1);
				seg.deleteIdx(1, 1, N, idx);
			} else {
				int l = readInt();
				int r = readInt();
				if (t != 4) {
					l -= r;
					r += (r + l);
				}
				l = seg.findidx(l);
				r = seg.findidx(r);
				if (t == 2) {
					seg.updateRange(1, 1, N, l, r, -seg.minQuery(1, 1, N, l, r));
				} else if (t == 3) {
					seg.updateRange(1, 1, N, l, r, seg.maxQuery(1, 1, N, l, r));
				} else if (t == 4) {
					sb.append(seg.thirdQuery(1, 1, N, l, r).high[2]).append('\n');
				}
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

class Seg {
	private int[] idxtree;
	private Node[] tree;
	private long[] lazy;
	public long[] arr;
	private int N;

	Seg(int N) {
		this.N = N;
		double treeh = Math.ceil(Math.log(N) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		idxtree = new int[N + 1];
		arr = new long[N + 1];
		lazy = new long[Math.toIntExact(treesize)];
		tree = new Node[Math.toIntExact(treesize)];

		for (int i = 1; i <= N; i++) {
			updateIdxTree(i, 1);
		}
	}

	public void updateIdxTree(int i, int val) {
		while (i < idxtree.length) {
			idxtree[i] += val;
			i += i & -i;
		}
	}

	public int query(int i) {
		int sum = 0;
		while (i > 0) {
			sum += idxtree[i];
			i -= i & -i;
		}
		return sum;
	}

	public int findidx(int idx) {
		int s = idx;
		int e = N;
		while (s < e) {
			int m = ((s + e) >> 1);
			int cnt = query(m);
			if (cnt < idx) {
				s = m + 1;
			} else {
				e = m;
			}
		}
		return s;
	}

	public void initLazySeg(int node, int start, int end) {
		if (start == end) {
			tree[node] = new Node(arr[start]);
			return;
		}
		int nextNode = node << 1;
		int mid = (start + end) >> 1;
		initLazySeg(nextNode, start, mid);
		initLazySeg(nextNode + 1, mid + 1, end);
		tree[node] = new Node(-1);
		tree[node].update(tree[nextNode], tree[nextNode + 1]);
	}

	public long maxQuery(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);
		if (end < left || right < start)
			return 0;
		else if (left <= start && end <= right) {
			if(tree[node].islive)
				return tree[node].high[0];
			return 0;
		} else {
			int nextNode = node << 1;
			int mid = (start + end) >> 1;
			return Math.max(maxQuery(nextNode, start, mid, left, right),
					maxQuery(nextNode + 1, mid + 1, end, left, right));
		}
	}

	public long minQuery(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);
		if (end < left || right < start)
			return Long.MAX_VALUE;
		else if (left <= start && end <= right) {
			if(tree[node].islive)
				return tree[node].min;
			return Long.MAX_VALUE;
		} else {
			int nextNode = node << 1;
			int mid = (start + end) >> 1;
			return Math.min(minQuery(nextNode, start, mid, left, right),
					minQuery(nextNode + 1, mid + 1, end, left, right));
		}
	}

	public Node thirdQuery(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);
		if (end < left || right < start) {
			Node del = new Node(0);
			del.delete();
			return del;
		} else if (left <= start && end <= right) {
			if(tree[node].islive)
				return tree[node];
			
			Node del = new Node(0);
			del.delete();
			return del;
		} else {
			Node temp = new Node(0);
			int nextNode = node << 1;
			int mid = (start + end) >> 1;
			Node l = thirdQuery(nextNode, start, mid, left, right);
			Node r = thirdQuery(nextNode + 1, mid + 1, end, left, right);
			temp.update(l, r);
			return temp;
		}
	}
	
	public void deleteIdx(int node , int start , int end , int idx) {
		updateLazy(node, start, end);
		if (idx > end || idx < start) {
			return;
		}

		if(start == end) {
			tree[node].delete();
			return;
		}

		int nextNode = node << 1;
		int mid = (start + end) >> 1;
		deleteIdx(nextNode, start, mid, idx);
		deleteIdx(nextNode + 1, mid + 1, end, idx);
		tree[node].update(tree[nextNode], tree[nextNode + 1]);
	}

	public void updateRange(int node, int start, int end, int left, int right, long val) {
		updateLazy(node, start, end);
		int nextNode = node << 1;
		if (left > end || right < start) {
			return;
		} else if (left <= start && right >= end) {
			tree[node].add(val);
			if (start != end) {
				lazy[nextNode] += val;
				lazy[nextNode + 1] += val;
			}
			return;
		}

		int mid = (start + end) >> 1;
		updateRange(nextNode, start, mid, left, right, val);
		updateRange(nextNode + 1, mid + 1, end, left, right, val);
		tree[node].update(tree[nextNode], tree[nextNode + 1]);
	}

	private void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node].add(lazy[node]);
			if (start != end) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	class Node {
		boolean islive;
		long min;
		long[] high;

		public Node(long c) {
			this.islive = true;
			this.min = c;
			this.high = new long[3];
			this.high[0] = c;
			this.high[1] = -1;
			this.high[2] = -1;
		}

		public void add(long val) {
			if(this.islive) {
				this.min += val;
				this.high[0] += val;
				if(high[1] != -1) {
					this.high[1] += val;
				}
				if(high[2] != -1) {
					this.high[2] += val;
				}
			}
		}
		
		public void delete() {
			this.islive = false;
		}

		public void update(Node a, Node b) {
			if(!a.islive && !b.islive) {
				this.islive = false;
			} else if(a.islive && b.islive) {
				this.min = Math.min(a.min, b.min);
				int acnt = 0;
				int bcnt = 0;
				long val = 0;
				for (int i = 0; i < 3; i++) {
					val = a.high[acnt];
					if (a.high[acnt] > b.high[bcnt]) {
						acnt++;
					} else if (a.high[acnt] < b.high[bcnt]) {
						val = b.high[bcnt++];
					} else {
						acnt++;
						bcnt++;
					}
					this.high[i] = val;
				}
			} else if(a.islive) {
				this.min = a.min;
				this.high[0] = a.high[0];
				this.high[1] = a.high[1];
				this.high[2] = a.high[2];
			} else if(b.islive) {
				this.min = b.min;
				this.high[0] = b.high[0];
				this.high[1] = b.high[1];
				this.high[2] = b.high[2];
			}
		}
	}
}