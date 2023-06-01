import java.util.ArrayList;

public class Main {
	public static ArrayList<Node>[] g; 
	public static int[][][] parent;
	public static boolean[] visit;
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int size = 1;
		for (int i = 2; i < 19; i++) {
			size *= 2;
			if(size >= N) {
				size = i;
				break;
			}
		}

		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		
		g = new ArrayList[N+1];
		visit = new boolean[N+1];
		parent = new int[N+1][size][2];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			g[a].add(new Node(b , c));
			g[b].add(new Node(a , c));
		}
		
		dfs(1);
		int temp = 0;
		int cost = 0;
		for (int i = 1; i < size; i++) {
			for (int j = 1; j <= N; j++) {
				temp = parent[j][i-1][0];
				cost = parent[j][i-1][1];
				parent[j][i][0] = parent[temp][i-1][0];
				parent[j][i][1] = parent[temp][i-1][1] + parent[j][i-1][1];
			}
		}
		
		int v = 0;
		for (int i = 1; i <= N; i++) {
			cost = arr[i];
			v = i;
			for (int j = size-1; j >= 0; j--) {
				if(parent[v][j][0] != 0 && parent[v][j][1] <= cost) {
					cost -= parent[v][j][1];
					v = parent[v][j][0];
				}
			}
			sb.append(v).append('\n');
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	public static void dfs(int v) {
		visit[v] = true;
		
		for (int i = 0; i < g[v].size(); i++) {
			if(!visit[g[v].get(i).v]) {
				parent[g[v].get(i).v][0][0] = v;
				parent[g[v].get(i).v][0][1] = g[v].get(i).cost;
				dfs(g[v].get(i).v);
			}
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
	int cost;
	public Node(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
}