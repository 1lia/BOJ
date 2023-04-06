import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		if(N == 1) {
			System.out.println(1);
			return;
		}	
		int[] dp = new int[N];
		dp[0] = 1;	
		dp[1] = 2;
		
		for (int i = 2; i < N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		System.out.println(dp[N-1]);
	}
}