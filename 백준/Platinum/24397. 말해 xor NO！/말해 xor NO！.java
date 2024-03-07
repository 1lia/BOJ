public class Main {
	public static int[][] trie;
	public static long[][] count;
	public static int piv , K;
	public static long res;

	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		K = readInt();
		trie = new int[31 * N + 1][2];
		count = new long[31 * N + 1][2];
		for (int i = 0; i < N; i++) {
			put(readInt());
		}
		for (int i = 0; i < M; i++) {
			query(readInt());
		}
		System.out.println(res);
	}

	public static void query(int n) {
		int bit = 1 << 30;
		int sum = 0;
		int p = 0;
		int t = 0;
		while (bit > 0) {
			t = (bit & n) == 0 ? 0 : 1;
			if (sum + bit < K) {
				res += count[p][t];
				sum += bit;
				t ^= 1;	
			} 
			if(bit == 1)
				res += count[p][t];
			
			if((p = trie[p][t]) == 0)
				return;
			bit >>= 1;
		}
	}

	public static void put(int n) {
		int bit = 1 << 30;
		int p = 0;
		while (bit > 0) {
			int t = (bit & n) == 0 ? 0 : 1;
			if (trie[p][t] == 0)
				trie[p][t] = ++piv;
			
			count[p][t]++;
			p = trie[p][t];		
			bit >>= 1;
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