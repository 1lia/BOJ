import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		MST mst = new MST(N);
		for (int i = 0; i < M; i++) {
			mst.putNode(readInt(), readInt(), readInt());
		}
		System.out.println(mst.run());
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
	public ArrayList<Node>[] g;
	public boolean[] visit;
	public int[] dist;
	public int N;
	public MST(int N){
		this.N = N;
		g = new ArrayList[N+1];
		visit = new boolean[N+1];
		dist = new int[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
	}
	
	public int run() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		int result = 0;
		int t = 0;
		q.offer(new Node(1,0));
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(!visit[node.v]) {
				visit[node.v] = true;
				result += node.cost;
				t = Math.max(t, node.cost);
				for (int i = 0; i < g[node.v].size(); i++) {
					if(!visit[g[node.v].get(i).v] && dist[g[node.v].get(i).v] == 0 || dist[g[node.v].get(i).v] > g[node.v].get(i).cost ) {
						q.offer(g[node.v].get(i));
					}
				}
			}
		}
		return result - t;
	}
	
	public void putNode(int a , int b , int cost) {
		g[a].add(new Node(b , cost));
		g[b].add(new Node(a , cost));
	}
	
	class Node implements Comparable<Node>{
		int v;
		int cost;
		
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}