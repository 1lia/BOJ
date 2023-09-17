public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		SegmentTree seg = new SegmentTree(1000000);
		while(N-->0) {
			if(readInt() == 1) {
				seg.update(1, 1, 1000000, readInt(), 1);
			} else {
				seg.update(1, 1, 1000000, readInt(), -1);
			}
			sb.append(seg.query(1, 1, 1000000)).append('\n');
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
	public int[][] tree;
	
	SegmentTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new int[Math.toIntExact(treesize)][2];
	}
	
	void update(int node , int start , int end , int index , long value) {
		if(index < start || end < index)
			return;
		
		if(index == start && end == index) {
			tree[node][0] += value;
			tree[node][1] += value;
		} else {
			update(node << 1 , start , (start + end) >> 1 , index , value);
			update((node << 1) + 1 , ((start + end) >> 1) + 1 , end , index , value);
			tree[node][0] = Math.min(tree[node << 1][0] , tree[(node << 1) + 1][0]);
			tree[node][1] = tree[node << 1][1] + tree[(node << 1) + 1][1];
		}
	}
	
	int query(int node , int start , int end) {
		if(start == end) {
			return tree[node][1];
		}

		if(tree[node << 1][0] == 0) {
			return query(node << 1 , start , (start + end) >> 1); 
		} else {
			return query((node << 1) + 1 , ((start + end) >> 1) + 1 , end) + tree[node << 1][1];
		}
	}
}