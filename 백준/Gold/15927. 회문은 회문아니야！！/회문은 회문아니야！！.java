import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		boolean flag = true;

		int s = 0;
		int e = str.length() - 1;
		char c = str.charAt(0);

		while (e - s >= 0) {
			if (str.charAt(s) != c || str.charAt(e) != c) {
				flag = false;
			}
			if (str.charAt(e--) != str.charAt(s++)) {
				System.out.println(str.length());
				return;
			}
		}
		if (flag)
			System.out.println(-1);
		else
			System.out.println(str.length() - 1);
	}
}