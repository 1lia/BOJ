public class Main{
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int Q = readInt();
		int U = readInt();
		int V = readInt();
		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt() * U + V;
		}
		SegTree seg = new SegTree(N);
		seg.init(arr, 1, 1, N);
		for (int i = 0; i < Q; i++) {
			if(readInt() == 0) {
				sb.append(seg.query(1, 1 , N , readInt(), readInt()) - V).append('\n');
			} else {
				seg.update(1, 1, N, readInt(), readInt() * U + V);
			}
		}
		sb.deleteCharAt(sb.length()-1);
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

class SegTree{
	private Node[] tree;
	SegTree(int n){
		double h = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long size = Math.round(Math.pow(2, h));
		tree = new Node[Math.toIntExact(size)];
	}

	void init(int[] arr , int node , int start , int end){
		if (start == end) {
			tree[node] = new Node(arr[start] , arr[start] , arr[start] , arr[start]);
		}
		else {
			init(arr, node << 1 , start , (start + end) >> 1);
			init(arr,(node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			tree[node] = merge(tree[node << 1] , tree[(node << 1) + 1]);
		}
	}

	void update(int node , int start , int end , int index , int value) {
		if (index < start || end < index) {
			return;
		} else if(start == index && end == index) {
			tree[node].sum = value;
			tree[node].lm = value;
			tree[node].rm = value;
			tree[node].max = value;
		} else {
			update(node << 1 , start , (start + end) >> 1 , index , value);
			update((node << 1) + 1, ((start + end) >> 1) + 1 , end , index , value);
			tree[node] = merge(tree[node << 1] , tree[(node << 1) + 1]);
		}
	}
	
	private Node merge(Node L , Node R) {
		Node node = new Node();
		node.sum = L.sum + R.sum;
		node.lm = Math.max(L.lm, L.sum + R.lm);
		node.rm = Math.max(R.rm, R.sum + L.rm);
		node.max = Math.max(L.max, Math.max(R.max, L.rm + R.lm));
		return node;
	}
	
	private Node find(int node , int start , int end , int left , int right) {
		if (end < left || right < start)
			return new Node(0 , -100000000 , -100000000 , -100000000);
		else if(left == start && end == right) {
			return tree[node];
		} else {
			int mid = (start + end) >> 1;
			return merge(find(node << 1 , start , mid , left , Math.min(mid, right)) , find((node << 1) + 1 , mid + 1 , end , Math.max(mid + 1 ,left) , right));
		}
	}
	
	int query(int node , int start , int end , int left , int right) {
		return find(node , start , end , left , right).max;
	}
	
	private class Node{
		int sum;
		int max;
		int rm;
		int lm;
		public Node() {}
		public Node(int sum, int max, int rm, int lm) {
			this.sum = sum;
			this.max = max;
			this.rm = rm;
			this.lm = lm;
		}
	}
}