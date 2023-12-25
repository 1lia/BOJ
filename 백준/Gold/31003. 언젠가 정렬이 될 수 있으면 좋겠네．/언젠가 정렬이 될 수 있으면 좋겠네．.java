import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		PriorityQueue<Node> q = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		ArrayList<Integer>[] g = new ArrayList[N];
		int[] arr = new int[N];
		int[] degree = new int[N];
		
		for (int i = 0; i < N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if(gcd(arr[i] , arr[j])) {
					g[i].add(j);
					degree[j]++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(degree[i] == 0) {
				q.offer(new Node(i , arr[i]));
			}
		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < g[node.v].size(); i++) {
				int next = g[node.v].get(i);
				if(--degree[next] == 0) {
					q.offer(new Node(next , arr[next]));
				}
			}
			sb.append(node.val).append(' ');
		}
		System.out.println(sb);
	}
	public static boolean gcd(int a , int b) {
		int t = 0;
		while(b != 0) {
			t = a % b;
			a = b;
			b = t;
		}
		return a == 1 ? false : true;
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

class Node implements Comparable<Node>{
	int v;
	int val;
	public Node(int v, int val) {
		this.v = v;
		this.val = val;
	}
	@Override
	public int compareTo(Node o) {
		return this.val - o.val;
	}
}