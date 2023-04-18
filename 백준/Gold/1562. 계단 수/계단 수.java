import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N + 1][10][1 << 10];
		boolean[][][] visit = new boolean[N + 1][10][1 << 10];
		int div = 1000000000;

		for (int i = 1; i < 10; i++) {
			dp[1][i][1 << i] = 1;
		}

		int temp = 0;
//		N개수
		for (int i = 2; i <= N; i++) {
//			숫자
			for (int j = 0; j < 10; j++) {

				temp = j - 1;
				if (temp >= 0) {
					for (int b = 0; b < 1024; b++) {
						dp[i][j][b | (1 << j)] += dp[i - 1][temp][b];
						dp[i][j][b | (1 << j)] %= div;
					}
				}

				temp = j + 1;
				if (temp < 10) {
					for (int b = 0; b < 1024; b++) {
						dp[i][j][b | (1 << j)] += dp[i - 1][temp][b];
						dp[i][j][b | (1 << j)] %= div;
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < 10; i++) {
			result += dp[N][i][1023];
			result %= div;
		}
		System.out.println(result);	
	}
}
