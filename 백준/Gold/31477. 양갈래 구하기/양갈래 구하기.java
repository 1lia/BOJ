import java.util.*;

public class Main {
	public static ArrayList<Pos>[] g;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		g = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		for (int i = 1; i < N; i++) {
			int A = readInt();
			int B = readInt();
			int V = readInt();
			g[A].add(new Pos(B , V));
			g[B].add(new Pos(A , V));
		}
		dfs(1,1,0);
	}
	
	public static int dfs(int v , int p , int up) {
		int cost = 0;
		for (int i = 0; i < g[v].size(); i++) {
			Pos next = g[v].get(i);
			if(next.v != p) {
				cost += dfs(next.v , v , next.cost);
			}
		}
		
		if(v == 1)
			System.out.println(cost);

		if(cost == 0) {
			return up;
		}
		
		return Math.min(cost, up);
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

class Pos{
	int v;
	int cost;
	public Pos(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
}