import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static int[][] dp;
	public static int[][] maxdp;
	public static int N;
	public static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[N][N];
		maxdp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dp[i][j] == 0)
					result = Math.max(dfs(i,j,1) , result);
			}
		}
		System.out.println(result);
	}
	
	public static int dfs(int x , int y , int cnt) {
		dp[x][y] = cnt;
		maxdp[x][y] = Math.max(maxdp[x][y], cnt);
		int[][] dxy = {{1,-1,0,0 } , {0,0,1,-1}}; 
		for (int i = 0; i < 4; i++) {
			int dx = x + dxy[0][i];
			int dy = y + dxy[1][i];
			if(dx >= 0 && dx < N && dy >= 0 && dy < N) {
				if(arr[x][y] < arr[dx][dy]) {
					if(dp[dx][dy] == 0) {
						maxdp[x][y] = Math.max(dfs(dx , dy , cnt + 1) , maxdp[x][y]);
					}
					else {
						maxdp[x][y] = Math.max(maxdp[x][y], maxdp[dx][dy] + cnt + 1 - dp[dx][dy]);
					}
				}
			}
		}
		return maxdp[x][y];
	}
}