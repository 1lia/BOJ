import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<Integer>[] g;
	public static int[] match;
	public static int[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] board = new String[N];
		int[][] row = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine();
		}
		
		int t = 1;
		for (int i = 0; i < N; i++) {
			boolean check = false;
			for (int j = 0; j < M; j++) {
				if(board[i].charAt(j) == '*') {
					row[i][j] = t;
					check = true;
				} else if(check) {
					t++;
					check = false;
				}
			}
			if(check)
				t++;
		}
		g = new ArrayList[t];
		visit = new int[t];
		for (int i = 1; i < t; i++) {
			g[i] = new ArrayList<>();
		}
		t = 1;

		for (int i = 0; i < M; i++) {
			boolean check = false;
			for (int j = 0; j < N; j++) {
				if(board[j].charAt(i) == '*') {
					g[row[j][i]].add(t);
					check = true;
				} else if(check) {
					t++;
					check = false;
				}
			}
			if(check)
				t++;
		}
		match = new int[t];
		int res = 0;
		for (int i = 1; i < g.length; i++) {
			if(dfs(i,i)) {
				res++;
			}
		}
		System.out.println(res);
	}
	
	public static boolean dfs(int v , int c) {
		visit[v] = c;
		
		for (int i = 0; i < g[v].size(); i++) {
			int nv = g[v].get(i);
			if(match[nv] == 0 || (visit[match[nv]] != c && dfs(match[nv] , c))) {
				match[nv] = v;
				return true;
			}
		}
		return false;
	}
}