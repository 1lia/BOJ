public class Main {
	public static int N;
	public static int[][] arr;
	public static int[] size;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		sb = new StringBuilder();
		N = readInt();
		arr = new int[N + 1][N + 1];
		size = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = readInt();
				size[i] += arr[i][j];
			}
		}

		for (int i = 1; i <= N; i++) {
			if ((size[i] & 1) == 1) {
				System.out.println(-1);
				return;
			}
		}
		dfs(1);
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}

	public static void dfs(int v) {
		for (int i = 1; i <= N; i++) {
			while (arr[v][i] != 0) {
				arr[v][i]--;
				arr[i][v]--;
				dfs(i);
			}
		}
		sb.append(v).append(' ');
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