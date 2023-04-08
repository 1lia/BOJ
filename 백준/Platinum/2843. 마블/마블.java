import java.util.Arrays;

class Main {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		int N = readInt();
		UnionFind uf = new UnionFind(N);
		int[] E = new int[N+1];
		boolean[] fail = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			E[i] = readInt();
			if(E[i] == 0)
				E[i] = i;
		}

		int Q = readInt();
		int[][] query = new int[Q][2];
		int cnt = 0;
		
		for (int i = 0; i < Q; i++) {
			query[i][0] = readInt();
			query[i][1] = readInt();
			if(query[i][0] == 2) {
				fail[query[i][1]] = true;
			} else {
				cnt++;
			}
		}
		int[] result = new int[cnt--];

		for (int i = 1; i <= N; i++) {
			if(!fail[i] && E[i] != i)  {
				if(uf.Union(E[i], i))
					uf.merge(0, E[i]);
				else
					uf.merge(E[i], i);
			}	
		}

		for (int i = Q - 1; i >= 0; i--) {
			if(query[i][0] == 1) {
				result[cnt--] = uf.find(query[i][1]);
			} else {
				if(uf.Union(query[i][1] , E[query[i][1]]))
					uf.merge(0 , query[i][1]);
				else
					uf.merge(E[query[i][1]] , query[i][1]);
			}
		}
		
		
		for (int i = 0; i < result.length; i++) {
			if(result[i] == 0) {
				sb.append("CIKLUS").append('\n');
			} else {
				sb.append(result[i]).append('\n');
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