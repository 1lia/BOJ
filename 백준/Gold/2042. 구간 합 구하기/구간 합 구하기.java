public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt() + readInt();
		long[] arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readLong();
		}
		Seg seg = new Seg(N);
		seg.init(arr, 1, 1, N);
		for (int i = 0; i < M; i++) {
			if (readInt() == 1) {
				seg.update(1, 1, N, readInt(), readLong());
			} else {
				sb.append(seg.query(1, 1, N, readInt(), readInt())).append('\n');
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

class Seg{
	private long[] tree;
	
	Seg(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new long[Math.toIntExact(treesize)];
	}
	
	void init(long[] arr , int node , int start , int end) {
		if(start == end)
			tree[node] = arr[start];
		else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			init(arr , next , start , mid);
			init(arr , next + 1 , mid + 1 , end);
			tree[node] = tree[next] + tree[next + 1];
		}
	}
	
	void update(int node , int start , int end , int index , long value) {
		if(index < start || end < index)
			return;
		
		if(index == start && end == index) {
			tree[node] = value;
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			update(next , start , mid , index , value);
			update(next + 1 , mid + 1 , end , index , value);
			tree[node] = tree[next] + tree[next + 1];
		}
	}
	
	long query(int node , int start , int end , int left , int right) {
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			return query(next , start , mid , left , right) + query(next + 1 , mid + 1 , end , left , right);
		}
	}
}