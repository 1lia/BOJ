import java.io.*;
import java.util.*;
public class Main {
	public static int[][] g;
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s = "";
		g = new int[1001][1001];
		while((s = br.readLine()) != null) {
			st = new StringTokenizer(s);
			int N = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			for (int i = 0; i <= 1000; i++) {
				Arrays.fill(g[i], 0);
			}
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				g[v][u] = w;
				g[u][v] = w;
			}
			sb.append(dfs(C , C)).append('\n');
		}
		System.out.println(sb);
	}
	public static int dfs(int v , int u){
		int sum = 0;
		for (int i = 1; i < 1001; i++) {
			if(g[v][i] != 0 && i != u) {
				int tep = dfs(i , v);
				if(tep == 0) {
					sum += g[v][i];
				} else {
					sum += Math.min(tep , g[v][i]);
				}
			}
		}
		return sum;
	}
}