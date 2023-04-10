public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int Q = readInt();
		
		int[][] query = new int[Q + N][3];	
		int[] arr = new int[N + 1];
		String[] result = new String[Q];
		arr[1] = 1;
		UnionFind uf = new UnionFind(N);
		
		for (int i = 2; i <= N; i++) {
			arr[i] = readInt();
		}
		
		for (int i = 0; i < Q + N - 1; i++) {
			query[i][0] = readInt();
			if(query[i][0] == 0) {
				query[i][1] = readInt();
			} else {
				query[i][1] = readInt();
				query[i][2] = readInt();
			}
		}
		
		int cnt = Q-1;
		for (int i = Q + N - 1; i >= 0; i--) {
			if(query[i][0] == 0) {
				uf.merge(query[i][1], arr[query[i][1]]);
			} else {
				if(uf.Union(query[i][1], query[i][2])) {
					result[cnt--] = "YES";
				} else {
					result[cnt--] = "NO";
				}
			}
		}
		
		for (int i = 0; i < Q; i++) {
			sb.append(result[i]).append('\n');
		}
		
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


class UnionFind {
	public static int[] arr;

	UnionFind(int n) {
		arr = new int[n + 1];
		init();
	}

	//찾는 과정
	private void init() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}

	public int find(int x) {
		if (arr[x] == x)
			return x;

		arr[x] = find(arr[x]);
		return arr[x];
	}

	//합치기
	public void merge(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;
		arr[y] = x;
		return;
	}

	//확인
	public boolean Union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return true;
		return false;
	}

}