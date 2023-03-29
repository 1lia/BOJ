import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    static int[][] dp;
    static int N;
    static int[][] distance;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		N = Integer.parseInt(bf.readLine());
		distance = new int[N][N];
		dp = new int[N][1 << N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0, 1));

	}

	public static int dfs(int n, int visit) {
		if (dp[n][visit] != 0) {
			return dp[n][visit];
		}

		if (visit == (1 << N) - 1) {
			if(distance[n][0] == 0)
				return 100000000;
			return distance[n][0];
		}

		dp[n][visit] = 100000000;

		for (int i = 1; i < N; i++) {
			if ((visit & (1 << i)) != 0) {
				
			} else if(distance[n][i] == 0){
				
			} else {
				dp[n][visit] = Math.min(dp[n][visit], dfs(i, visit | (1 << i)) + distance[n][i]);
			}
		}
		return dp[n][visit];
    }
}