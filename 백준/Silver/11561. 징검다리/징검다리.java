public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		long T = readLong();
		while (T-- > 0) {
			long N = readLong();
			long s = 1;
			long e = 1000000000L;
			long m = 0;
			long r = 0;
			while (s <= e) {
				m = (s + e) >> 1;
				r = ((m * (m + 1)) >> 1);
				
				if(r <= N) {
					s = m+1;
				} else {
					e = m-1;
				}
			}
			sb.append(e).append('\n');
		}

		System.out.println(sb);
	}

	public static long readLong() throws Exception {
		long val = 0;
		long c = System.in.read();
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