import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		ArrayList<ArrayList<Query>> query = new ArrayList<>();
		query.add(new ArrayList<>());
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(readInt() == 1) {
				query.get(cnt).add(new Query(readInt() , readInt()));
			} else {
				cnt++;
				query.add(new ArrayList<>());
			}
		}
		
		int s = 0;
		int e = cnt;
		while(s < e) {
			TwoSat twoSat = new TwoSat(100000);
			int m = ((s + e) >> 1);
			for (int i = 0; i <= m; i++) {
				for (Query q : query.get(i)) {
					twoSat.putEdge(q.a, q.b);
				}
			}
			twoSat.run();
			if(twoSat.isTwoSat()) {
				s = m + 1;
			} else {
				e = m;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cnt; i++) {
			if(i < s) {
				sb.append("YES DINNER\n");
			} else {
				sb.append("NO DINNER\n");
			}
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
}

class TwoSat{
	public ArrayList<Integer>[] g , back;
	public int[] visit , group;
	public int check , N , cnt , size;
	public Stack<Integer> stack;
	public ArrayList<ArrayList<Integer>> res;
	
	TwoSat(int N){
		this.N = N;
		size = (N << 1);
		stack = new Stack<>();
		g = new ArrayList[size + 1];
		back = new ArrayList[size + 1];
		visit = new int[size + 1];
		group = new int[size + 1];
		for (int i = 0; i <= size; i++) {
			g[i] = new ArrayList<>();
			back[i] = new ArrayList<>();
		}
		res = new ArrayList<>();
	}
	
	public int not(int v) {
		if(v <= N) {
			return v + N;
		} else {
			return v - N;
		}
	}

	public void putEdge(int u , int v) {
		if(u < 0) {
			u = -u + N;
		}
		if(v < 0) {
			v = -v + N;
		}

		g[not(u)].add(v);
		g[not(v)].add(u);
		back[v].add(not(u));
		back[u].add(not(v));
	}
	
	public void run() {
		makeSCC();
		makeGroup();
	}
	
	public boolean isTwoSat() {
		for (int i = 1; i <= N; i++) {
			if(group[i] == group[i + N]) {
				return false;
			}
		}
		return true;
	}
	
	private void makeGroup() {
		for (int i = 0; i < res.size(); i++) {
			for (int v : res.get(i)) {
				group[v] = i;
			}
		}
	}
	
	private void makeSCC() {
		++check;
		for (int i = 1; i <= size; i++) {
			if(visit[i] != check) {
				visit[i] = check;
				dfs1(i);
			}
		}
		++check;
		while(!stack.isEmpty()) {
			int v = stack.pop();
			if(visit[v] != check) {
				visit[v] = check;
				res.add(new ArrayList<>());
				dfs2(v);
				++cnt;
			}
		}
	}
	
	private void dfs1(int v) {
		for (int next : g[v]) {
			if(visit[next] != check) {
				visit[next] = check;
				dfs1(next);
			}
		}
		stack.add(v);
	}
	
	private void dfs2(int v) {
		res.get(cnt).add(v);
		for (int next : back[v]) {
			if(visit[next] != check) {
				visit[next] = check;
				dfs2(next);
			}
		}
	}
}

class Query{
	int a;
	int b;
	public Query(int a, int b) {
		this.a = a;
		this.b = b;
	}
}