public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = (int) readLong();
		long K = readLong();
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readLong();
		}
		long s = 1;
		long e = 2000000000L;
		long m = 0;
		while (s != e) {
			m = (s + e) >> 1;
			
			long res = (m * (m + 1)) >> 1;
			for (int i = 1; i < N; i++) {
				long day = arr[i] - arr[i - 1];

				if (day >= m) {
					res += (m * (m + 1)) >> 1;
				} else {
					long t = m - day;
					res += (m * (m + 1)) >> 1;
					res -= (t * (t + 1)) >> 1;
				}

				if (res >= K) {
					break;
				}
			}

			if (res >= K) {
				e = m;
			} else {
				s = m + 1;
			}
		}

		System.out.println(e);
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