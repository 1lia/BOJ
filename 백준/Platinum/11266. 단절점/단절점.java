import java.util.ArrayList;
public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] visit;
	public static int cnt;
	public static boolean[] result;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int V = readInt();
		int E = readInt();
		g = new ArrayList[V+1];
		visit = new int[V+1];
		result = new boolean[V+1];
		for (int i = 0; i <= V; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		
		for (int i = 1; i <= V; i++) {
			if(visit[i] == 0) {
				dfs(i , i);
			}
		}
		sb.append('\n');
		int count = 0;
		for (int i = 1; i <= V; i++) {
			if(result[i]) {
				count++;
				sb.append(i).append(' ');	
			}
		}
		sb.insert(0, count);
		System.out.println(sb);
	}
	
	public static int dfs(int v , int pa) {
		int num = ++cnt;
		visit[v] = num;
		int count = 0;
		for (int i = 0; i < g[v].size(); i++) {
			if(visit[g[v].get(i)] == 0) {
				int t = dfs(g[v].get(i) , v);
				count++;
				if(t >= visit[v] && v != pa)
					result[v] = true;
				
				num = Math.min(num, t);
			} else if(g[v].get(i) != pa) {
				num = Math.min(visit[g[v].get(i)], num);
			}
		}
		
		if(v == pa && count > 1)
			result[v] = true;
		return num;
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