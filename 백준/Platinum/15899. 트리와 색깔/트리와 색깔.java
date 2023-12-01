import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		int res = 0;
		int N = readInt();
		int M = readInt(); readInt();
		int[] color = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			color[i] = readInt();
		}

//		트리를 피고 구간재설정
		ETT ett = new ETT(N);
		for (int i = 1; i < N; i++) {
			ett.putEdge(readInt(), readInt());
		}
		ett.dfs(1,1);
		
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[ett.num[i]] = color[i];
		}
		
//		k이하개수
		MergeSortTree tree = new MergeSortTree(N);
		tree.init(arr, 1, 1, N);

		for (int i = 0; i < M; i++) {
			int v = readInt();
			res += tree.query(1, 1, N, ett.et[ett.num[v]].s, ett.et[ett.num[v]].e, readInt());
			res %= 1000000007;
		}
		System.out.println(res);
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

class ETT {
	ArrayList<Integer>[] g;
	int[] num;   //정점의 방문순서
	int cnt;
	Pos[] et;
	int[] depth;
	boolean[] visit;

	public ETT(int N) {
		g = new ArrayList[N + 1];
		num = new int[N + 1];
		et = new Pos[N + 1];
		depth = new int[N + 1];
		visit = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
	}

	public void dfs(int v, int dep) {
		visit[v] = true;
		cnt++;
		num[v] = cnt;
		int t = cnt;
		et[t] = new Pos();
		et[t].s = cnt;
		depth[cnt] = dep;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if (!visit[next])
				dfs(next, dep + 1);
		}
		et[t].e = cnt;
	}

	public void putEdge(int a, int b) {
		g[a].add(b);
		g[b].add(a);
	}

	class Pos {
		int s;
		int e;
	}
}

class MergeSortTree {
	public int[][] tree;

	MergeSortTree(int n) {
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new int[Math.toIntExact(treesize)][];
	}

	int[] init(int[] arr, int node, int start, int end) {
		if (start == end) {
			tree[node] = new int[1];
			tree[node][0] = arr[start];
			return tree[node];
		} else {
			int next = node << 1;
			int mid = (start + end) >> 1;
			int[] ar1 = init(arr, next, start, mid);
			int[] ar2 = init(arr, next + 1, mid + 1, end);
			tree[node] = new int[ar1.length + ar2.length];

			int leftindex = 0;
			int rightindex = 0;
			int cnt = 0;
			while (leftindex < ar1.length && rightindex < ar2.length) {
				if (ar1[leftindex] < ar2[rightindex]) {
					tree[node][cnt++] = ar1[leftindex++];
				} else {
					tree[node][cnt++] = ar2[rightindex++];
				}
			}

			while (leftindex < ar1.length) {
				tree[node][cnt++] = ar1[leftindex++];
			}

			while (rightindex < ar2.length) {
				tree[node][cnt++] = ar2[rightindex++];
			}
			return tree[node];
		}
	}

	int query(int node, int start, int end, int left, int right, int k) {
		if (end < left || right < start)
			return 0;
		else if (left <= start && end <= right) {
			return binarySearch(tree[node], k);
		} else {
			int next = node << 1;
			int mid = (start + end) >> 1;
			return query(next, start, mid, left, right, k) + query(next + 1, mid + 1, end, left, right, k);
		}
	}
	
	
	int binarySearch(int[] arr , int k) {
		if(arr[arr.length-1] <= k) {
			return arr.length;
		}
		int s = 0;
		int e = arr.length - 1;
		int m = 0;
		
		while(s < e) {
			m = ((s + e) >> 1);
			if(arr[m] <= k) {
				s = m + 1;
			} else {
				e = m;
			}
		}
		return s;
	}
}