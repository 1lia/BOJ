public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		long[] arr = new long[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		int t = 0;
		for (int i = 1; i <= N; i++) {
			arr[i] -= t;
			t += arr[i];
		}
		
		SegTree seg = new SegTree(N);
		seg.init(arr, 1, 1, N);
		int Q = readInt();
		
		
//		imos로 update하면 c의 합을 구하는query는 1 ~ c 의 합을 구하는 query가 됨 
		for (int i = 0; i < Q; i++) {
			if(readInt() == 1) {
				int l = readInt();
				int r = readInt();
				seg.update_range(1, 1, N, l, r, 1);
				seg.update_range(1, 1, N, r + 1, r + 1, - r + l - 1);
			} else {
				int c = readInt();
				sb.append(seg.query(1, 1, N, 1, c)).append('\n');
			}
		}
		
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}

class SegTree{
	private long[] tree;
	private long[] lazy;
	
	SegTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new long[(int)treesize];
		lazy = new long[(int)treesize];
	}
	
	void init(long[] arr , int node , int start , int end){
		if (start == end) {
			tree[node] = arr[start];
		}
		else {
			init(arr, node << 1 , start , (start + end) >> 1); 
			init(arr,(node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			tree[node] = tree[node << 1] +  tree[(node << 1) + 1]; 
		}
	}

	void update_range(int node , int start , int end , int left , int right , long value) {
		update_lazy(node , start , end);
		if (left > end || right < start) {
			return;
		} else if(left <= start && right >= end ) {
			tree[node] += (end - start + 1) * value;
			if(start != end) {
				lazy[node << 1] += value;
				lazy[(node << 1) + 1] += value;
			}
			return;
		}
		update_range(node * 2 , start , (start + end) >> 1 , left , right , value);
		update_range(node * 2 + 1, ((start + end) >> 1) + 1 , end , left , right , value);
		tree[node] =  tree[node * 2] + tree[node * 2 + 1];
	}

	void update_lazy(int node , int start , int end){
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if(start != end) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	long query(int node , int start , int end , int left , int right) {
		update_lazy(node , start , end);
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			return query(node << 1 , start , (start + end) >> 1 , left , right) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
		}
	}
}