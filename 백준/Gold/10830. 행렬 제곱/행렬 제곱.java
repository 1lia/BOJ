public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = (int) readLong();
		long B = readLong();
		MatrixPower mp = new MatrixPower(N, 1000);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				mp.matrix[i][j] = readLong();
			}
		}
		long[][] res = mp.run(B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(res[i][j] % 1000).append(' ');
			}
			sb.append('\n');
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

class MatrixPower {
	public long[][] matrix;
	private long MOD;
	private int N;

	public MatrixPower(int n, long mod) {
		MOD = mod;
		N = n;
		matrix = new long[N][N];
	}

	long[][] run(long n) {
		if (n == 1)
			return matrix;

		long[][] t = run(n >> 1);

		if ((n & 1) == 0) {
			return power(t, t);
		}
		return power(power(t, t), matrix);
	}

	long[][] power(long[][] a, long[][] b) {
		long[][] t = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					t[i][j] += (a[i][k] * b[k][j]);
					t[i][j] %= MOD;
				}
			}
		}
		return t;
	}
}