import java.util.ArrayList;
import java.util.Arrays;
public class Main {
	public static ArrayList<Integer>[] g;
	public static boolean[] visit;
	public static int[][] dp;
	public static int size;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		size = (int) (Math.log(N-1) / Math.log(2)) + 2;
		g = new ArrayList[N+1];
		visit = new boolean[N+1];
		dp = new int[N+1][size];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < size; j++) {
				dp[i][j] = j;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		dfs(1);
		int result = Integer.MAX_VALUE;
		for (int i = 1; i < size; i++) {
			if(dp[1][i] < result)
				result = dp[1][i];
		}
		System.out.println(result);
	}
	
	public static int[] dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < g[v].size(); i++) {
			if(!visit[g[v].get(i)]) {
				int[] temp = dfs(g[v].get(i));
				int min = Integer.MAX_VALUE;
				int min2 = Integer.MAX_VALUE;
				int index = 0;

				for (int j = 1; j < size; j++) {
					if(temp[j] < min) {
						if(min < min2) {
							min2 = min;
						}
						min = temp[j];
						index = j;							
					} else if(temp[j] < min2) {
						min2 = temp[j];
					}
				}
				
				for (int j = 1; j < size; j++) {
					if(j == index) {
						dp[v][j] += min2;
					} else {
						dp[v][j] += min;
					}
				}
			}
		}
		return dp[v];
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