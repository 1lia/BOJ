import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = readInt();
			int M = readInt();
			int K = readInt();
			
			if(N == 0)
				break;
			
			MST mst1 = new MST(N, M);
			MST mst2 = new MST(N, M);
			
			for (int i = 0; i < M; i++) {
				int c = readInt();
				int a = readInt();
				int b = readInt();
				if(c == 18) {
					mst1.putNode(a, b, 0);
					mst2.putNode(a, b, 1);
				} else {
					mst1.putNode(a, b, 1);
					mst2.putNode(a, b, 0);
				}
			}
			if(mst2.run() <=  K && K <= N - 1 - mst1.run()) {
				sb.append(1).append('\n');
			} else {
				sb.append(0).append('\n');
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

class MST{
	public Node[] g;
	public int N;
	public int M;
	public int cnt;
	public MST(int N , int M){
		this.N = N;
		this.M = M;
		g = new Node[M];
	}
	
	public int run() {
		Arrays.sort(g);
		UnionFind set = new UnionFind(N);
		int result = 0;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(!set.Union(g[i].a , g[i].b)) {
				set.merge(g[i].a , g[i].b);
				result += g[i].cost; 
				cnt++;
			}
			
			if(cnt + 1 == N)
				break;
		}
		
		return result;
	}
	
	public void putNode(int a , int b , int cost) {
		g[cnt++] = new Node(a,b,cost);
	}
	
	class Node implements Comparable<Node>{
		int a;
		int b;
		int cost;
		
		public Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}

class UnionFind {
	public static int[] arr;

	UnionFind(int n) {
		arr = new int[n + 1];
		init();
	}

	private void init() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}

	private int find(int x) {
		if (arr[x] == x)
			return x;

		arr[x] = find(arr[x]);
		return arr[x];
	}

	public void merge(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;
		arr[y] = x;
		return;
	}

	public boolean Union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return true;
		return false;
	}

}