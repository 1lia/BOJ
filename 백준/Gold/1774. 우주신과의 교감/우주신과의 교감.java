import java.util.*;

public class Main {
	public static int[] dsu;
	public static void main(String[] args) throws Exception {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		int N = readInt();
		int M = readInt();
		long[][] arr = new long[2][N + 1];
		dsu = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dsu[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			arr[0][i] = readLong();
			arr[1][i] = readLong();
		}
		for (int i = 0; i < M; i++) {
			merge(readInt() , readInt());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				q.offer(new Pos(i , j , Math.sqrt(Math.pow(Math.abs(arr[0][i] - arr[0][j]), 2) + Math.pow(Math.abs(arr[1][i] - arr[1][j]), 2))));
			}
		}
		double res = 0;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(!union(p.a, p.b)) {
				res += p.cost;
				merge(p.a, p.b);
			}
		}
		System.out.print(String.format("%.2f", res));
		
	}
	public static void merge(int a , int b) {
		a = find(a);
		b = find(b);
		dsu[b] = a;
	}
	
	public static boolean union(int a , int b) {
		a = find(a);
		b = find(b);
		if(a == b)
			return true;
		return false;
	}
	public static int find(int a) {
		if(dsu[a] == a)
			return a;
		return dsu[a] = find(dsu[a]);
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
	
	public static long readLong() throws Exception {
		long val = 0;
		long c = System.in.read();
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

class Pos implements Comparable<Pos>{
	int a;
	int b;
	double cost;
	
	public Pos(int a, int b, double cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Pos o) {
		if(this.cost > o.cost) {
			return 1;
		}
		return -1;
	}
}