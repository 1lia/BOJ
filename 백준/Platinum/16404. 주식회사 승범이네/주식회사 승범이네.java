import java.util.ArrayList;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] num;
	public static int cnt;
	public static Pos[] et;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		g = new ArrayList[N+1];
		num = new int[N+1];
		et = new Pos[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		readInt();
		for (int i = 2; i <= N; i++) {
			g[readInt()].add(i);
		}
		dfs(1);
		SegmentTree seg = new SegmentTree(N);
		int v = 0;
		for (int i = 0; i < M; i++) {
			int t = readInt();
			v = num[readInt()];
			if(t == 1) {
				seg.update_range(1, 1, N, et[v].s, et[v].e, readInt());
			} else {
				sb.append(seg.query(1, 1, N, v, v)).append('\n');
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
	public static void dfs(int v){
	    cnt++;
	    num[v]=cnt;
	    int t=cnt;
	    et[t] = new Pos();
	    et[t].s=cnt;
	    for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			dfs(next);
		}
	    et[t].e=cnt;
	}
}
class Pos{
	public int s;
	public int e;
}
class SegmentTree{
	private long[] tree;
	private long[] lazy;
	
	SegmentTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new long[Math.toIntExact(treesize)];
		lazy = new long[Math.toIntExact(treesize)];
	
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
		update_range(node << 1 , start , (start + end) >> 1 , left , right , value);
		update_range((node << 1) + 1, ((start + end) >> 1) + 1 , end , left , right , value);
		tree[node] =  tree[node << 1] + tree[(node << 1) + 1];
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