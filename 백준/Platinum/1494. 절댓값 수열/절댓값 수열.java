public class Main {
	public static long a, b, t, N;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		long A = readLong();
		long B = readLong();
		N = readLong();
		for (long z = 0; z < N; z++) {
			a = A;
			b = B;
			t = readLong();
			while (t > 0) {		
				long temp = Math.abs(a - b);
				a = b;
				b = temp;
				t--;
				run();
			}
			sb.append(a).append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	public static void run() {
		if (a != 0 && b != 0) {
			if (a >= (b << 1)) {
				long min = Math.min(a / (b << 1), t / 3);
				a -= min * b * 2;
				t -= min * 3;
			}
		} else {
			t %= 3;
		}
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