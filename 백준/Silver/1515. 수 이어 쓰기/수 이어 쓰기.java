import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int cnt = 0;
		int result = 0;
		for (int i = 1; i < 900000000; i++) {
			String s1 = Integer.toString(i);
			for (int j = 0; j < s1.length(); j++) {
				if(s.charAt(cnt) == s1.charAt(j)) {
					cnt++;
				}
				if(cnt == s.length()) {
					result = i;
					break;
				}
			}
			if(result != 0) {
				break;
			}
		}
		System.out.println(result);
	}
}