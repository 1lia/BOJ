public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
        SegmentTree seg = new SegmentTree(N);
        seg.init(arr, 1, 1, N);
        int M = readInt();
        for (int i = 0; i < M; i++) {
			if(readInt() == 1) {
				seg.update(1, 1, N, readInt(), readInt());
			} else {
				int[] t = seg.query(1, 1, N, readInt(), readInt());
				sb.append(t[0] + t[1]).append('\n');
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

class SegmentTree{
	private int[][] tree;
	
	SegmentTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new int[Math.toIntExact(treesize)][2];
	}
	
	void init(int[] arr , int node , int start , int end) {
		if(start == end)
			tree[node][0] = arr[start];
		else {
			init(arr , node << 1 , start , (start + end) >> 1);
			init(arr , (node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			updateNode(node);
		}
	}
	
	void update(int node , int start , int end , int index , int value) {
		if(index < start || end < index)
			return;
		
		if(index == start && end == index) {
			tree[node][0] = value;
		} else {
			update(node << 1 , start , (start + end) >> 1 , index , value);
			update((node << 1) + 1 , ((start + end) >> 1) + 1 , end , index , value);
			updateNode(node);
		}
	}
	
	void updateNode(int node){
		int a = tree[node << 1][0];
		int b = tree[(node << 1) + 1][0];
		int c = tree[node << 1][1];
		int d = tree[(node << 1) + 1][1];
		
		if(a > b) {
			tree[node][0] = a;
			tree[node][1] = Math.max(b, c);
		} else {
			tree[node][0] = b;
			tree[node][1] = Math.max(a, d);
		}
	}
	
	int[] query(int node , int start , int end , int left , int right) {
		if (end < left || right < start)
			return new int[] {0,0};
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			int[] t = {0,0};
			int[] a = query(node << 1 , start , (start + end) >> 1 , left , right);
			int[] b = query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
			
			if(a[0] > b[0]) {
				t[0] = a[0];
				t[1] = Math.max(a[1], b[0]);
			} else {
				t[0] = b[0];
				t[1] = Math.max(b[1], a[0]);
			}
			return t;
		}
	}
}