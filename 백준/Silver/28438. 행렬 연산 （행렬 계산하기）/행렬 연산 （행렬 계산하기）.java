public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		int[] row = new int[N+1];
		int[] col = new int[M+1];
		int Q = readInt();
		for (int i = 0; i < Q; i++) {
			if(readInt() == 1) {
				row[readInt()]+=readInt();
			} else {
				col[readInt()]+=readInt();
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(row[i] + col[j]).append(' ');
			}
			sb.append('\n');
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