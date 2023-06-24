public class Main {
	public static int[][] arr;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		arr = new int[N][N];
		if ((N & 1) == 1) {
			run(N);
		} else {
			if ((N & 2) == 0) {
				int s = N >> 2;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						arr[i][j] = j + 1 + (N * i);
						if (i >= (N >> 1)) {
							if (i >= N - s) {
								if (s <= j && j < N - s) {
									int t = arr[i][j];
									arr[i][j] = arr[N - i - 1][N - j - 1];
									arr[N - i - 1][N - j - 1] = t;
								}
							} else {
								if (j < s || N - s <= j) {
									int t = arr[i][j];
									arr[i][j] = arr[N - i - 1][N - j - 1];
									arr[N - i - 1][N - j - 1] = t;
								}
							}
						}
					}
				}
			} else {
				run(N >> 1);
				int x = N >> 1;
				int y = N >> 1;
				int t = x * y;
				for (int i = N >> 1; i < N; i++) {
					for (int j = N >> 1; j < N; j++) {
						arr[i][j] = arr[i - x][j - y] + t;
					}
				}

				for (int i = N >> 1; i < N; i++) {
					for (int j = N >> 1; j < N; j++) {
						arr[i][j] = arr[i - x][j - y] + t;
					}
				}

				for (int i = 0; i < x; i++) {
					for (int j = y; j < N; j++) {
						arr[i][j] = arr[i + x][j] + t;
					}
				}

				for (int i = x; i < N; i++) {
					for (int j = 0; j < y; j++) {
						arr[i][j] = arr[i - x][j + y] + t;
					}
				}
				
				int max = y >> 1;
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < max; j++) {
						if (i != x >> 1) {
							arr[i][j] = arr[i][j] ^ arr[i+x][j];
							arr[i+x][j] = arr[i][j] ^ arr[i+x][j];
							arr[i][j] = arr[i][j] ^ arr[i+x][j];
						} else {
							arr[i][j+1] = arr[i][j+1] ^ arr[i+x][j+1];
							arr[i+x][j+1] = arr[i][j+1] ^ arr[i+x][j+1];
							arr[i][j+1] = arr[i][j+1] ^ arr[i+x][j+1];
						}
					}
					
					for (int j = N - (x >> 1) + 1 ; j < N; j++) {
						arr[i][j] = arr[i][j] ^ arr[i+x][j];
						arr[i+x][j] = arr[i][j] ^ arr[i+x][j];
						arr[i][j] = arr[i][j] ^ arr[i+x][j];
					}
				}

			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}

	public static void run(int n) {
		int x = n - 1;
		int y = n >> 1;
		int max = n * n;
		int cnt = 1;
		while (cnt <= max) {
			arr[x][y] = cnt++;
			int dx = x + 1;
			int dy = y + 1;
			if (dx >= n) {
				dx = 0;
			}
			if (dy >= n) {
				dy = 0;
			}
			if (arr[dx][dy] != 0) {
				dx = x - 1;
				dy = y;
				if (0 > dx) {
					dx = n - 1;
				}
			}
			x = dx;
			y = dy;
		}
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