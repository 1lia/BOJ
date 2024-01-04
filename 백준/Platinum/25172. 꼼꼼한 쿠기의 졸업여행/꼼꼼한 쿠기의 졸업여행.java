import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	public static int[] p;
	public static void main(String[] args) throws Exception {
		HashSet<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		int[] query = new int[N + 1];
		p = new int[N + 1];
		String[] res = new String[N + 1];
		Arrays.fill(p, -1);
		ArrayList<Integer>[] g = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		
		for (int i = N; i > 0; i--) {
			query[i] = readInt();
		}
		set.add(query[1]);
		res[0] = "DISCONNECT";
		res[1] = "CONNECT";
		for (int i = 2; i <= N; i++) {
			int v = query[i];
			set.add(v);
			for (int j = 0; j < g[v].size(); j++) {
				int next = g[v].get(j);
				if(set.contains(next)) {
					merge(v, next);
				}
			}
			if(size(v) == i) {
				res[i] = "CONNECT";
			} else {
				res[i] = "DISCONNECT";
			}
		}
		
		for (int i = N; i >= 0; i--) {
			sb.append(res[i]).append('\n');
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
	
	
	public static int find(int n) {
		if(p[n] < 0)
			return n;
		return p[n] = find(p[n]);
	}
	
	public static void merge(int a , int b) {
		a = find(a);
		b = find(b);
		if (a == b) 
			return;
	    
	    p[b] += p[a];
	    p[a] = b;
	}

	public static int size(int n) { 
		return -p[find(n)]; 
	}
}