public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[] arr = new int[N];
		long[] cnt = new long[3];

		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
			if(arr[i] <= 1) {
				cnt[arr[i]]++;
			} else {
				cnt[2]++;
			}
		}
		long res = 0;
		for (int i = 0; i + 1 < N; i++) {
			if(arr[i] <= 1) {
				cnt[arr[i]]--;
			} else {
				cnt[2]--;
			}
			res += cnt[0];
			if(arr[i] == 0) {
				res += (cnt[1] << 1);
				res += cnt[2];
			} else if(arr[i] == 1) {
				res += cnt[0];
			} 
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