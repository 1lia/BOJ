import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int V = readInt();
		int E = readInt();
		int[] arr = new int[10];
		long[] time = new long[10];
		ArrayList<Node>[] g = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			g[i] = new ArrayList<>();
		}
		
		while(E-->0) {
			int u = readInt();
			int v = readInt();
			long w = readLong();
			g[u].add(new Node(v , w));
			g[v].add(new Node(u , w));
		}
		
		for (int i = 0; i < 10; i++) {
			arr[i] = readInt();
		}
		int s = readInt();
		int start = 0;
		int end = 0;
		long[] cost = new long[V + 1];
		
		while(start < 9) {
			Arrays.fill(cost , Long.MAX_VALUE);
			pq.offer(new Node(arr[start] , 0));
			cost[arr[start]] = 0;
			while(!pq.isEmpty()) {
				Node node = pq.poll();
			
				if(node.w > cost[node.v])
					continue;
				
				for (int i = 0; i < g[node.v].size(); i++) {
					Node next = g[node.v].get(i);
					if(node.w + next.w < cost[next.v]) {
						cost[next.v] = node.w + next.w;
						pq.offer(new Node(next.v , cost[next.v]));
					}
				}
			}
			
			end = start + 1;
			while(end <= 9 && cost[arr[end]] == Long.MAX_VALUE) {
				time[end] = -1;
				if(end == 9)
					break;
				end++;
			}
			if(cost[arr[end]] != Long.MAX_VALUE)
				time[end] = cost[arr[end]] + time[start];
			start = end;
		}
		Arrays.fill(cost , Long.MAX_VALUE);
		pq.offer(new Node(s , 0));
		cost[s] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.w > cost[node.v])
				continue;
			
			for (int i = 0; i < g[node.v].size(); i++) {
				Node next = g[node.v].get(i);
				if(node.w + next.w < cost[next.v]) {
					cost[next.v] = node.w + next.w;
					pq.offer(new Node(next.v , cost[next.v]));
				}
			}
		}
		int res = V + 1;
		for (int i = 0; i < 10; i++) {
			if(time[i] >= cost[arr[i]]) {
				res = Math.min(arr[i] , res);
			}
		}
		if(res == V + 1) {
			res = -1;
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

class Node implements Comparable<Node>{
	int v;
	long w;
	public Node(int v, long w) {
		this.v = v;
		this.w = w;
	}
	@Override
	public int compareTo(Node o) {
		return this.w - o.w > 0 ? 1 : -1;
	}
}