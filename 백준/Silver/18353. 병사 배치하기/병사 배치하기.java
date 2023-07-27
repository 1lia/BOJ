public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[] res = new int[N];
		int cnt = 0;
		int t = 0;
		res[0] = readInt();
		for (int i = 1; i < N; i++) {
			int a = readInt();
			if(res[cnt] > a) {
				res[++cnt] = a;
			} else {
				t = 0;
				while(t <= cnt) {
					if(res[t] <= a) {
						res[t] = a;
						break;
					}
					t++;
				}
			}
		}
		System.out.println(N-cnt-1);
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