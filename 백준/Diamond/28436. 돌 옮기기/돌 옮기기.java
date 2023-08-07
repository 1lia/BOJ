import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			char c = '.';
			int t = 0;
			for (int j = s.length() - 1; j >= 0; j--) {
				if (s.charAt(j) != '.') {
					c = s.charAt(j);
					t = j;
					break;
				}
			}
			long W = 0;
			long B = 0;
			long cnt = 1;

			for (int j = t-1; j >= 0; j--) {
				if (c == s.charAt(j)) {
					cnt++;
				} else if (s.charAt(j) == '.') {
					if (c == 'W') {
						W += cnt;
					} else if (c == 'B') {
						B += cnt;
					}
				} else {
					if(cnt == 0) {
						cnt = 1;
					} else {
						cnt = 0;
					}	
					c = s.charAt(j);
				}
			}
			if (W > B) {
				sb.append("WHITE").append('\n');
			} else {
				sb.append("BLACK").append('\n');
			}

		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}