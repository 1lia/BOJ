public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		SegTree seg = new SegTree(N);
		for (int i = 0; i < M; i++) {
			int o = readInt();
			int s = readInt();
			int t = readInt();
			if(o == 1) {
				sb.append(seg.query(1, 1, N, s, t)).append('\n');
			} else {
				seg.update_range(1, 1, N, s, t);
			}
		}
		sb.deleteCharAt(sb.length() - 1);
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
	private int[] tree;
	private int[] lazy;

	SegTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		int size = (int) Math.round(Math.pow(2, treeh));
		tree = new int[size];
		lazy = new int[size];
	
	}

//  구간갱신
	void update_range(int node , int start , int end , int left , int right) {
		update_lazy(node , start , end);
		if (left > end || right < start) {
			return;
		} else if(left <= start && right >= end ) {
			tree[node] = (end - start + 1) - tree[node]; //개수반전
			if(start != end) {
				lazy[node << 1] += 1;
				lazy[(node << 1) + 1] += 1;
			}
			return;
		}
		update_range(node * 2 , start , (start + end) >> 1 , left , right);
		update_range(node * 2 + 1, ((start + end) >> 1) + 1 , end , left , right);
		tree[node] =  tree[node * 2] + tree[node * 2 + 1];
	}

	private void update_lazy(int node , int start , int end){
		if(lazy[node] % 2 == 1) {
			tree[node] = (end - start + 1) - tree[node];
			if(start != end) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	int query(int node , int start , int end , int left , int right) {
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