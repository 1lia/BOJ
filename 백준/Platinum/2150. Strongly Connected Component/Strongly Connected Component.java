import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] visit;
	public static boolean[] fin; 
	public static Stack<Integer> s;
	public static int cnt;
	public static int id;
	public static ArrayList<ArrayList<Integer>> st = new ArrayList<>(); 
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int V = readInt();
		int E = readInt();
		g = new ArrayList[V+1];
		visit = new int[V+1];
		fin = new boolean[V+1];
		s = new Stack<Integer>();
		st.add(new ArrayList<>());
		
		for (int i = 1; i <= V; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			g[readInt()].add(readInt());
		}
		
		for (int i = 1; i <= V; i++) {
			if(visit[i] == 0) {
				dfs(i);
			}
		}

		Pos[] result = new Pos[cnt]; 
		for (int i = 0; i < cnt; i++) {
			result[i] = new Pos(i , st.get(i).get(0));
		}
		
		Arrays.sort(result);
		sb.append(cnt).append('\n');
		for (int i = 0; i < cnt; i++) {
			int idx = result[i].index;
			for (int j = 0; j < st.get(idx).size(); j++) {
				sb.append(st.get(idx).get(j)).append(' ');
			}
			sb.append(-1).append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
	public static int dfs(int v) {
		visit[v] = ++id;
		int result = visit[v];
		s.push(v);
		
		for (int i = 0; i < g[v].size(); i++) {
			if(visit[g[v].get(i)] == 0) {
				result = Math.min(result, dfs(g[v].get(i)));
				
			} else if(!fin[g[v].get(i)]){
				result = Math.min(result, visit[g[v].get(i)]);
			}
		}
		
		if(result == visit[v]) {
			while(!s.isEmpty()) {
				int a = s.pop();
				fin[a] = true;
				st.get(cnt).add(a);
				
				if(a == v) {
					break;
				}
			}
			Collections.sort(st.get(cnt));
			st.add(new ArrayList<>());
			cnt++;
		}
		return result;
	}

	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}

class Pos implements Comparable<Pos>{
	int index;
	int n;
	public Pos(int index, int n) {
		this.index = index;
		this.n = n;
	}
	@Override
	public int compareTo(Pos o) {
		return this.n - o.n;
	}
}