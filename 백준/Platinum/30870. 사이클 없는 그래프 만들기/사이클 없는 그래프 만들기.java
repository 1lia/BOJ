import java.util.*;

public class Main {
	public static ArrayList<Node>[] g;
	public static int[] set;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int K = readInt();
		
		Queue<Integer> q = new ArrayDeque<>();
		int[] time = new int[N + 1];
		set = new int[N + 1];
		ArrayList<Query> query = new ArrayList<>();
		g = new ArrayList[N + 1];
		HashSet<Integer> hash = new HashSet<>();
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
			set[i] = i;
		}
		
		for (int i = 1; i <= M; i++) {
			int u = readInt();
			int v = readInt();
			g[u].add(new Node(v, i));
			g[v].add(new Node(u, i));
		}
		
		for (int i = 0; i < K; i++) {
			int k = readInt();
			q.offer(k);
			time[k] = 1;
		}
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < g[v].size(); i++) {
				Node next = g[v].get(i);
				if(!hash.contains(next.idx)) {
					query.add(new Query(v, next.v, time[v]));
					hash.add(next.idx);
				}
				
				if(time[next.v] == 0) {
					time[next.v] = time[v] + 1;
					q.offer(next.v);
				}
			}
		}
		for (int i = query.size() - 1; i >= 0; i--) {
			Query qq = query.get(i);
			if(union(qq.u , qq.v)) {
				System.out.println(qq.t);
				return;
			}
			merge(qq.u , qq.v);
		}
	}
	
	public static int find(int a) {
		if(a == set[a]) {
			return a;
		}
		return set[a] = find(set[a]);
	}
	
	public static boolean union(int a , int b) {
		if(find(b) == find(a)) {
			return true;
		}
		return false;
	}
	
	public static void merge(int a , int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			set[a] = b;
		}
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

class Node{
	int v;
	int idx;
	public Node(int v,int idx) {
		this.v = v;
		this.idx = idx;
	}
}


class Query{
	int u;
	int v;
	int t;
	public Query(int u, int v, int t) {
		this.u = u;
		this.v = v;
		this.t = t;
	}
}