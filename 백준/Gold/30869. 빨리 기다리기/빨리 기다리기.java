import java.util.*;

public class Main {
	public static ArrayList<Node>[] graph;
	public static long[][] dp;   //정점에서 k번의 값
	public static boolean[] visit;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int K = readInt();
		graph = new ArrayList[N + 1];
		dp = new long[N + 1][K + 1];
		visit = new boolean[N + 1];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			graph[readInt()].add(new Node(readInt() , readLong() , readLong()));
		}
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		dp[1][0] = 0;
		int v = 1;
		while(v != 0) {
			visit[v] = true;
			for (int i = 0; i <= K; i++) {
				if(dp[v][i] != Long.MAX_VALUE) {
					for (int j = 0; j < graph[v].size(); j++) {
						Node node = graph[v].get(j);
						long temp = dp[v][i] % node.g;
						if(temp != 0) {
							temp = node.g - temp;
						}
						dp[node.v][i] = Math.min(dp[node.v][i] , dp[v][i] + temp + node.t);
						if(i != K) {
							dp[node.v][i + 1] = Math.min(dp[node.v][i + 1] , dp[v][i] + node.t);
						}
					}
				} else {
					break;
				}
			}
			v = 0;
			long t = Long.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				if(!visit[i]) {
					for (int j = 0; j <= K; j++) {
						if(dp[i][j] < t) {
							t = dp[i][j];
							v = i;
						}
					}
				}
			}
		}
		long res = Long.MAX_VALUE;
		for (int i = 0; i <= K; i++) {
			res = Math.min(res, dp[N][i]);
		}
		if(res == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
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

class Node {
	int v;
	long t;
	long g;
	public Node(int v, long t, long g) {
		this.v = v;
		this.t = t;
		this.g = g;
	}
}