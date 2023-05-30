import java.util.Arrays;
import java.util.HashSet;

public class Main{
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		int N = readInt(); int M = readInt(); int K = readInt();
		int[] arr = new int[N+1];
		int[] round = new int[M+1];
		String[] result = new String[K];
		UnionFind set = new UnionFind(N);
		HashSet<Integer> hash = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		for (int i = 1; i <= M; i++) {
			round[i] = readInt();
			if(arr[round[i]] != -1) {
				if(hash.contains(round[i])) {
					round[i] = 0;
				} else {
					hash.add(round[i]);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(arr[i] != -1 && !hash.contains(i)) {
				set.merge(i, arr[i]);
			}
		}

		Student[] query = new Student[K];
		for (int i = 0; i < K; i++) {
			int r = readInt();
			int a = readInt();
			int b = readInt();
			query[i] = new Student(r, a, b , i);
		}		
		Arrays.sort(query);
		int r = M;
		for (int i = 0; i < K; i++) {
			while(query[i].r < r) {
				if(round[r] != 0 && arr[round[r]] != -1) {
					set.merge(round[r] , arr[round[r]]);
				}
				r--;
			}
			
			if(set.Union(query[i].a, query[i].b)) {
				result[query[i].idx] = "Same Same;";
			} else {
				result[query[i].idx] = "No;";
			}
		}
		
		for (int i = 0; i < K; i++) {
			sb.append(result[i]).append('\n');
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

class Student implements Comparable<Student>{
	int r;
	int a;
	int b;
	int idx;

	public Student(int r, int a, int b, int idx) {
		super();
		this.r = r;
		this.a = a;
		this.b = b;
		this.idx = idx;
	}

	@Override
	public int compareTo(Student o) {
		return o.r - this.r;
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