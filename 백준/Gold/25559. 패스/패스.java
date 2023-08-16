public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		if(N == 1) {
			System.out.println(1);
			return;
		}
		if((N & 1) == 1) {
			System.out.println(-1);
			return;
		}
		for (int i = 0; i < N; i++) {
			if((i & 1) == 0) {
				sb.append(N-i).append(' ');
			} else {
				sb.append(i).append(' ');
			}
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