public class Main {
	public static void main(String[] args) throws Exception {	
		long MOD = 1_000_000_007;
		int N = readInt();
		int K = readInt();
		long[] pow = new long[N + 1];
		int[] arr = new int[K];
		for (int i = 0; i < N; i++) {
			arr[readInt() % K]++;
		}
		pow[0] = 1;
		for (int i = 1; i <= N; i++) {
			pow[i] = pow[i-1] * 2;
			pow[i] %= MOD;
		}
		int H = K >> 1;
		long res = arr[0] + 1;
		for (int i = 1; i < H; i++) {
			long t = (pow[arr[i]] + pow[arr[K - i]] - 1);
			t %= MOD;
			res *= t;
			res %= MOD;
		}
		
		if((K & 1) == 0) {
			res *= (arr[H] + 1);
		} else {
			long t = (pow[arr[H]] + pow[arr[K - H]] - 1);
			t %= MOD;
			res *= t;
		}
		res %= MOD;
		res -= (N + 1);
		if(res < 0) {
			res += MOD;
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