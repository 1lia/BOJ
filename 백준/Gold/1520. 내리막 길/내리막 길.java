import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[][] dp;
	public static int[][] map;
	public static int M;
	public static int N;
	public static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(0,0));
	}
	
	public static int dfs(int x , int y) {
//		도착 + 1
		if(x == M-1 && y == N-1)
			return 1;
		
		int[][] dxy = {{1,-1,0,0},{0,0,1,-1}};
		int dx = 0;
		int dy = 0;
		
		if(dp[x][y] == -1) {
			dp[x][y] = 0;
			
			for (int i = 0; i < 4; i++) {
				dx  = x + dxy[0][i];
				dy  = y + dxy[1][i];
				
				if(0 <= dx && dx < M && 0 <= dy && dy < N) {
					if(map[x][y] > map[dx][dy])
						dp[x][y] += dfs(dx,dy);
				}
			}
		}
		return dp[x][y];
	}
}