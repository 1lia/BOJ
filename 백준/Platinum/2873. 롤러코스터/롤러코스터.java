public class Main {
	public static StringBuilder odd, even, sb;
	public static void main(String[] args) throws Exception {
		sb = new StringBuilder();
		odd = new StringBuilder();
		even = new StringBuilder();
		int N = readInt();
		int M = readInt();
		if ((N & 1) == 1) {
			fullr(N >> 1, M);
		} else if ((M & 1) == 1) {
			fulld(N, M >> 1);
		} else {
			int[][] map = new int[N][M];
			int x = 0;
			int y = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = readInt();
					if (((i + j) & 1) == 1 && min > map[i][j]) {
						min = map[i][j];
						x = i;
						y = j;
					}
				}
			}

			int dx = x >> 1;
			int dy = y >> 1;
			for (int i = 1; i < M; i++) {
				odd.append('R');
				even.append('L');
			}
			N >>= 1;
			M >>= 1;
			for (int i = 0; i < dx; i++) {
				sb.append(odd).append('D').append(even).append('D');
			}

			for (int i = 0; i < dy; i++) {
				sb.append("DRUR");
			}

			if ((y & 1) == 0) {
				sb.append("RDR");
			} else {
				sb.append("DRR");
			}

			for (int i = dy + 1; i < M; i++) {
				sb.append("URDR");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append('D');

			for (int i = dx + 1; i < N; i++) {
				sb.append(even).append('D').append(odd).append('D');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		System.out.println(sb);
	}

	public static void fullr(int N, int M) {
		for (int i = 1; i < M; i++) {
			odd.append('R');
			even.append('L');
		}
		for (int i = 0; i < N; i++) {
			sb.append(odd).append('D').append(even).append('D');
		}
		sb.append(odd);
	}

	public static void fulld(int N, int M) {
		for (int i = 1; i < N; i++) {
			odd.append('D');
			even.append('U');
		}
		for (int i = 0; i < M; i++) {
			sb.append(odd).append('R').append(even).append('R');
		}
		sb.append(odd);
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