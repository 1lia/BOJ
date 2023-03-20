import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder result = new StringBuilder();
		String s1 = br.readLine();
		String s2 = br.readLine();

		int[][] dp = new int[2][s1.length() + 1];
		byte[][] tracking = new byte[s2.length() / 3 + 1][s1.length() + 1];
		int flag1 = 0;
		int flag2 = 1;
		for (int i = 1; i <= s1.length(); i++) {
			dp[0][i] = i;
		}
//			추가 = 0 삭제 = 1 수정 = 2 복사 = 3
//			세로 길이 s2는 세로
		int trackcnt = 0;
		int shift = 4;
//			몪 나머지	
//			byte 에 3개씩 담아줄거임 3의 배수로 증가할때마다 1개씩 늘어남 
		for (int i = 1; i <= s2.length(); i++) {
//				가로 길이  같으면 전에꺼 가져올수있음
			dp[flag2][0] = i;
			for (int j = 1; j <= s1.length(); j++) {
//					같을때 복사 
				if (s2.charAt(i - 1) == s1.charAt(j - 1)) {
					tracking[trackcnt][j] |= (byte) (3 << shift);
					dp[flag2][j] = dp[flag1][j - 1];
				} else {
//						이게제일같거나 작으면 수정
					if (dp[flag1][j - 1] <= dp[flag2][j - 1] && dp[flag1][j - 1] <= dp[flag1][j]) {
						tracking[trackcnt][j] |= (byte) (2 << shift);
						dp[flag2][j] = dp[flag1][j - 1] + 1;
					} else if (dp[flag1][j] <= dp[flag2][j - 1] && dp[flag1][j] <= dp[flag1][j - 1]) {
						tracking[trackcnt][j] |= (byte) (1 << shift);
						dp[flag2][j] = dp[flag1][j] + 1;
//							아래값이 작으면 추가
					} else if (dp[flag2][j - 1] <= dp[flag1][j] && dp[flag2][j - 1] <= dp[flag1][j - 1]) {
						tracking[trackcnt][j] |= (byte) (0 << shift);
						dp[flag2][j] = dp[flag2][j - 1] + 1;
//							왼쪽이 제일작으면 삭제
					} 
				}

//					4번째마다 다음 byte 값으로 넘겨줌
			}
			if (i % 3 == 0) {
				trackcnt++;
				shift = 4;
			} else {
				shift -= 2;
			}
//				교환
			flag1 = flag1 ^ flag2;
			flag2 = flag1 ^ flag2;
			flag1 = flag1 ^ flag2;
		}

//			마지막이 4로 끝나면 한단계 밑에 저장되있음 
		if (shift == 4) {
			trackcnt--;
			shift = 0;
		} else {
			shift += 2;
		}

		int a = trackcnt;
		int b = s1.length();
		int temp = 0;
//			b는 가로 a 는 세로
//			a 추가  d 삭제  m 수정  c 복사
		while ((a != 0 || shift != 6) && b != 0) {
//				shift만큼 밀어서 확인하고
			temp = (3 << shift) & tracking[a][b];
			temp = (temp >> shift);
//				System.out.println(a + " " + shift + " " + temp + " " + b);
			if (temp == 3) {
				sb.append('c');
				shift += 2;
				b--;
			} else if (temp == 2) {
				sb.append('m');
				shift += 2;
				b--;
			} else if (temp == 1) {
				sb.append('a');
				shift += 2;
			} else if (temp == 0) {
				sb.append('d');
				b--;
			}

			if (a == 0 && shift == 6) {
				break;
			}
			if (shift == 6) {
				a--;
				shift = 0;
			}
		}

		while (a > 0 || shift != 6) {
			sb.append('a');
			shift += 2;

			if (shift == 6 && a == 0) {
				break;
			} else if (shift == 6) {
				a--;
				shift = 0;
			}
		}

		while (b > 0) {
			b--;
			sb.append('d');
		}

		int s1cnt = 0;
		int s2cnt = 0;
//		System.out.println(sb.toString());
		for (int k = sb.length() - 1; k >= 0; k--) {
			if (sb.charAt(k) == 'd') {
				result.append(sb.charAt(k)).append(' ').append(s1.charAt(s1cnt)).append('\n');
				s1cnt++;
			} else if (sb.charAt(k) == 'c') {
				result.append(sb.charAt(k)).append(' ').append(s1.charAt(s1cnt)).append('\n');
				s1cnt++;
				s2cnt++;
			} else if (sb.charAt(k) == 'm') {
				result.append(sb.charAt(k)).append(' ').append(s2.charAt(s2cnt)).append('\n');
				s1cnt++;
				s2cnt++;
			} else {
				result.append(sb.charAt(k)).append(' ').append(s2.charAt(s2cnt)).append('\n');
				s2cnt++;
			}
		}
		result.deleteCharAt(result.length() - 1);
		System.out.println(result.toString());
//		System.out.println(dp[flag1][s1.length()]);

	}
}