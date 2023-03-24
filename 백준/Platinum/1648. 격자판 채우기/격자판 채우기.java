import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int[][] dp;
	public static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		if(((N * M) & 1) == 1) {
			System.out.println(0);
			System.exit(0);
		}
		size = N*M;
		dp = new int[14 * 14][1 << 14];
		for (int i = 0; i < 14 * 14; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(0, 0));
	}

	static int dfs(int cnt, int state) {
		if (cnt == size && state == 0)
			return 1;
		if(cnt >= size)
			return 0;

		if (dp[cnt][state] != -1)
			return dp[cnt][state];
		
		int result = 0;
		// 이미 채워져있으면 상태한칸이동
		if ((state & 1) == 1) {
			result = dfs(cnt + 1, (state >> 1));
		}
		// 채우는경우
		else {
//			가로로 채울때 옆에가 비어있거나 마지막칸이 아니어야함
			if (cnt % M < (M - 1) && (state & 2) == 0) {
				result += dfs(cnt + 2, state >> 2);
			}
//			세로로 채울때 아래는 무조건 비어있음
			result += dfs(cnt + 1, (state >> 1) | (1 << (M - 1)));
		}
		return dp[cnt][state] = result % 9901;
	}
}