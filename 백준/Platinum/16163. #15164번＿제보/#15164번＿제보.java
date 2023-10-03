import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(new Manacher().run(br.readLine()));
	}
}

class Manacher{
	public long run(String s) {
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
		long sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += (p[i] + 1) >> 1;
		}
		return sum;	
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