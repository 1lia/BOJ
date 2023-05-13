import java.util.ArrayList;
public class Main {
	public static void main(String[] args) throws Exception{
		int N = readInt();
		int M = readInt();
		UnionFind uf = new UnionFind(N);
		
		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			uf.merge(a, b);
		}
		int cnt = 0;
		ArrayList<Integer> arr = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			boolean flag = false;
			for (int j = 0; j < arr.size(); j++) {
				if(uf.Union(i, arr.get(j))) {
					flag = true;
					break;
				}	
			}
			if(!flag) {
				cnt++;
				arr.add(i);
			}
		}
		System.out.println(cnt);
	}
	
	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while(c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if(flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);
		
		if(flag)
			return -val;	
		return val;
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

	private int find(int x) {
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