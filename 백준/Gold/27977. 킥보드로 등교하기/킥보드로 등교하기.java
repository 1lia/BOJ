public class Main {
	public static void main(String[] args) throws Exception {
		int L = readInt();
		int N = readInt();
		int K = readInt();
		int mid = 0;
		int[] arr = new int[N+2];
		int cnt = 0;
		int ls = 0;
		int t = 0;
		arr[0] = 0;
		arr[N+1] = L;
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		
		int s = 0;
		for (int i = 1; i - 1 <= N; i++) {
			s = Math.max(arr[i] - arr[i-1], s);
		}
		int e = L;
		while(s != e) {
			mid = (s+e) >> 1;
			cnt = 0;
			ls = mid;
			t = 0;
			for (int i = 1; i - 1 <= N; i++) {
				if(arr[i] - t > ls) {
					cnt++;
					t = arr[i-1];
				}
			}
			if(cnt <= K) {
				e = mid;
			} else {
				s = mid + 1;
			}
		}
		System.out.println(e);
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