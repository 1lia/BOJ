public class Main {
	public static void main(String[] args) throws Exception {
		long K = readLong();
		long t = (long) Math.sqrt(K) + 1;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i = 2; i <= t; i++) {
			while(K % i == 0) {
				sb.append(i).append(' ');
				K /= i;
				cnt++;
			}
		}
		if(K != 1) {
			cnt++;
			sb.append(K);
		}
		System.out.println(cnt);
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