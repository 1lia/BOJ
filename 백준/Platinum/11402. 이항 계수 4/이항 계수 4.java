public class Main {
	public static void main(String[] args) throws Exception {
		long N = readLong();
		long K = readLong();
		int M = (int) readLong();
		int result = 1;
		if (N < K) {
			result = 0;
		} else {
			int[][] arr = new int[M][M];
			for (int i = 0; i < M; i++) {
				arr[i][0] = 1;
				for (int j = 1; j <= i; j++) {
					arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j]) % M;
				}
			}

			while (N != 0 || K != 0) {
				result *= arr[(int) (N % M)][(int) (K % M)];
				N /= M;
				K /= M;
				result %= M;
			}
		}
		System.out.println(result);

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