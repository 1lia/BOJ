import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] dp = new long[N+1];
		dp[a] = 1;
		dp[b] = -1;
		int state = 0;
		if(d > N)
			state = 1;
		for (int i = 1; i <= N; i++) {
			dp[i] += dp[i-1];
			dp[i] %= 1000;
			
			if(i+a <= N) {
				dp[i+a] += dp[i];
			}
			if(i+b <= N) {
				dp[i+b] -= dp[i];
			}
			
			state += dp[i];
			if(i >= d)
				state -= dp[i-d];
			
			if(dp[i] < 0)
				dp[i] += 1000;
			
			state %= 1000;
			if(state < 0)
				state += 1000;
		}
		System.out.println(state);
	}
}