import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Manacher m = new Manacher();
		System.out.println(m.run(s));
	}
}

class Manacher{
	public int run(String s) {
		s = make(s);
		int[] p = new int[s.length()];
		int n = s.length(), r = -1, k = -1;
		for (int i = 0; i < n; i++) {
			if (i <= r)
				p[i] = Math.min(r - i, p[2 * k - i]);
			while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n && s.charAt(i - p[i] - 1) == s.charAt(i + p[i] + 1))
				p[i]++;
			if (r < i + p[i]) {
				r = i + p[i];
				k = i;
			}
		}
		int max = 1;
		for (int i = 0; i < s.length(); i++) {
			if((i & 1) == 0) {
				max = Math.max(p[i], max);
			} else {
				max = Math.max(p[i], max);
			}
		}
		return max;	
	}
	
	public String make(String s){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			sb.append('!').append(s.charAt(i));
		}
		sb.append('!');
		return sb.toString();
	}
}