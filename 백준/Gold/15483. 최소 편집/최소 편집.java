import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		int[][] dp = new int[2][s1.length()+1];
		int flag1 = 0;
		int flag2 = 1;

		for (int i = 1; i <= s1.length(); i++) {
			dp[0][i] = i;
		}
		
//		세로 길이 s2는 세로 
		for (int i = 1; i <= s2.length(); i++) {
//			가로 길이  같으면 전에꺼 가져올수있음
			dp[flag2][0] = i;
			for (int j = 1; j <= s1.length(); j++) {
//				같을때
				if(s2.charAt(i-1) == s1.charAt(j-1)) {
					dp[flag2][j] = dp[flag1][j-1];
				} else {
					dp[flag2][j] = Math.min(dp[flag1][j], Math.min(dp[flag2][j-1] , dp[flag1][j-1])) + 1;
				}
			}
//		교환
			flag1 = flag1 ^ flag2;
			flag2 = flag1 ^ flag2;
			flag1 = flag1 ^ flag2;
		}
		System.out.println(dp[flag1][s1.length()]);

	}
}