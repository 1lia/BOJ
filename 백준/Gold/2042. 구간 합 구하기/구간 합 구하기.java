public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt() + readInt();
		Seg seg = new Seg(N);
		
		for (int i = 0; i < N; i++) {
			seg.tree[i + N] = readLong();
		}
		seg.init();
		for (int i = 0; i < M; i++) {
			if (readInt() == 1) {
				seg.update(readInt() - 1, readLong());
			} else {
				sb.append(seg.query(readInt() - 1, readInt() - 1)).append('\n');
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
	private int sz , H;
	public long[] tree;

	Seg(int N) {
		H = N;
		sz = N << 1;
		tree = new long[sz];
	}
	
	void init(){
		for (int i = H - 1; i > 0; i--) {
			int t = i << 1;
			tree[i] = tree[t] + tree[t + 1];
		}
	}
	
	void update(int v , long val) {
		v += H;
		tree[v] = val;

		for (int i = v >> 1; i > 0; i >>= 1) {
			int t = i << 1;
			tree[i] = tree[t] + tree[t + 1];
		}
	}
	
	long query(int l , int r) {
		long res = 0;
		l += H;
		r += H;

		while(l <= r) {
			if((l & 1) == 1) {
				res += tree[l++];
			}
			if((r & 1) == 0) {
				res += tree[r--];
			}
			l >>= 1;
			r >>= 1;
		}
		return res;
	}
}