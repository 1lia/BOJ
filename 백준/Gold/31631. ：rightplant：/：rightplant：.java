public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb =  new StringBuilder();
		int N = readInt();
		int[] arr = new int[N + 1];
		int s = 1;
		int e = N;
		int l = 0;
		int r = 0;

		while(N > 0) {
			if(l >= r) {
				arr[e--] = N;
				r += N;
			} else {
				arr[s++] = N;
				l += N;
			}
			N--;
		}
		
		for (int i = 1; i < arr.length; i++) {
			sb.append(arr[i]).append(' ');
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