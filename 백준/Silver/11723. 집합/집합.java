import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int M = Integer.parseInt(br.readLine());
		int bit = 0;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if("all".equals(s)) {
				bit = (1 << 21) - 1;
			} else if("empty".equals(s)) {
				bit = 0;
			} else {
				int x = Integer.parseInt(st.nextToken());
				if("add".equals(s)) {
					bit |= 1 << x;
				} else if("remove".equals(s)) {
					bit &= ~(1 << x);
				} else if("check".equals(s)) {
					sb.append((bit & (1 << x)) == 0 ? 0 : 1).append('\n');
				} else if("toggle".equals(s)) {
					bit ^= 1 << x;
				} 
			}
		}
		System.out.println(sb);
	}

	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}
}