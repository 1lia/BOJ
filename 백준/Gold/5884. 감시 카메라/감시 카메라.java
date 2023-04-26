import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static boolean[] visit;
	public static int[] match;
	public static ArrayList<Integer>[] g;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		Pos[] arr = new Pos[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new Pos(readInt() , readInt());
		}
		
		Comparator<Pos> comx = new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.x - o2.x;
			}
		};
		
		Comparator<Pos> comy = new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.y - o2.y;
			}
		};
		
		Arrays.sort(arr , comx);
		int t = arr[0].x;
		arr[0].x = 1;
		int cntx = 1;
		for (int i = 1; i < N; i++) {
			if(t != arr[i].x) {
				t = arr[i].x;
				arr[i].x = ++cntx;
			} else {
				arr[i].x = cntx;
			}
		}
		
		Arrays.sort(arr , comy);
		t = arr[0].y;
		arr[0].y = 1;
		int cnty = 1;
		for (int i = 1; i < N; i++) {
			if(t != arr[i].y) {
				t = arr[i].y;
				arr[i].y = ++cnty;
			} else {
				arr[i].y = cnty;
			}
		}

		g = new ArrayList[cntx+1]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			g[arr[i].x].add(arr[i].y);
		}
		
		match = new int[cnty+1];
		visit = new boolean[cntx+1];
		int cnt = 0;
		
		for (int i = 1; i <= cntx; i++) {
			Arrays.fill(visit, false);
			if(dfs(i))
				cnt++;

			if(cnt > 3) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}
	
	public static boolean dfs(int v) {
		visit[v] = true; 
		for (int i = 0; i < g[v].size(); i++) {
			int nxt = g[v].get(i);
			if(match[nxt] == 0 || (!visit[match[nxt]] && dfs(match[nxt]))) {
				match[nxt] = v;
				return true;
			}
		}
		return false;
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

class Pos{
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}