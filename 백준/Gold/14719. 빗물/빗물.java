public class Main {
	public static void main(String[] args) throws Exception {
		readInt();
		int W = readInt();
		int[] arr = new int[W];
		int[] l = new int[W];
		int[] r = new int[W];
		for (int i = 0; i < W; i++) {
			arr[i] = readInt();
		}
		int t = l[0];
		for (int i = 0; i < W; i++) {
			l[i] = t = Math.max(t, arr[i]);
		}
		t = r[0];
		for (int i = W - 1; i >= 0 ; i--) {
			r[i] = t = Math.max(t, arr[i]);
		}
		int res = 0;
		for (int i = 0; i < W; i++) {
			res += Math.min(l[i], r[i]) - arr[i];
		}
		System.out.println(res);
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