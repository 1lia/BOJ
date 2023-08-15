import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] match;
	public static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, String> hash = new HashMap<>();
		HashMap<Integer, Pos> vil = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][] arr = new int[N][M];
		int cnt = 0;
		int cntv = 0;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != '.' && map[i][j] != 'x') {
					sb.append(map[i][j]);
					arr[i][j] = cnt+1;
				} else {
					if(sb.length() > 0) {
						hash.put(++cnt, sb.toString());
						sb.setLength(0);
					}
				}
			}
			if(sb.length() > 0) {
				hash.put(++cnt, sb.toString());
				sb.setLength(0);
			}
		}
		

		g = new ArrayList[cnt+1];
		for (int i = 0; i <= cnt; i++) {
			g[i] = new ArrayList<>();
		}
		int[][] dxy = {{-1,-1,-1,0,0,1,1,1},{-1,0,1,-1,1,-1,0,1}};
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'x') {
					vil.put(++cntv, new Pos(i,j));
					set.clear();
					for (int k = 0; k < 8; k++) {
						int dx = dxy[0][k] + i;
						int dy = dxy[1][k] + j;
						if(0 <= dx && dx < N && 0 <= dy && dy < M && arr[dx][dy] != 0 && !set.contains(arr[dx][dy])) {
							g[arr[dx][dy]].add(cntv);
							set.add(arr[dx][dy]);
						}
					}
				}
			}
		}
		
		match = new int[cntv+1];
		visit = new boolean[cnt+1];
		for (int i = 1; i <= cnt; i++) {
			Arrays.fill(visit, false);
			dfs(i);
		}

		for (int i = 1; i <= cntv; i++) {
			sb.append(vil.get(i).x+1).append(' ').append(vil.get(i).y+1).append(' ').append(hash.get(match[i])).append('\n');
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	public static boolean dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < g[v].size(); i++) {
			int nx = g[v].get(i);
			if(match[nx] == 0 || (!visit[match[nx]] && dfs(match[nx]))) {
				match[nx] = v;
				return true;
			}
		}
		return false;
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