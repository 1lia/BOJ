import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		br.readLine();
		String str = br.readLine();
		int l = str.length();
		int Q = Integer.parseInt(br.readLine());
		int[][] dp = new int[l][26];
		dp[0][str.charAt(0) - 65]++;
		for (int i = 1; i < l; i++) {
			dp[i][str.charAt(i) - 65]++;
			for (int j = 0; j < 26; j++) {
				dp[i][j] += dp[i-1][j];
			}
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			int c = st.nextToken().charAt(0) - 65;
			long res = (a / l) * dp[l - 1][c];
			long len = a % l; //길이
			
			long a2 = a - 1;
			if((a & 1) == 0) {
				a >>= 1;
			} else {
				a2 >>= 1;
			}
			a %= l;
			a2 %= l;
			long s = (a * a2) % l;  //시작점
			if(0 < len) {
				int size = (int) (s + len) - 1;
				if(size < l) {
					res += dp[size][c];
					if(s > 0) {
						res -= dp[(int) s - 1][c];
					}
				} else {
					res += (dp[size % l][c] + dp[l-1][c] - dp[(int) s - 1][c]);
				}
			}
			sb.append(res).append('\n');
		}
		System.out.println(sb);
	}
}