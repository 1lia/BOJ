public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb =  new StringBuilder();
		int N = readInt();
		int[] arr = new int[N + 1];
		arr[1] = 1;
		arr[2] = 2;
		for (int i = 3; i <= N; i++) {
			int t = ((i - 1) >> 1) + 1;
			if(i % 4 == 0) {
				t++;
			}
			for (int j = i; j > t; j--) {
				arr[j] = arr[j-1] + 1;
			}
			arr[t] = 1;
			for (int j = t - 1; j > 0; j--) {
				arr[j]++;
			}
		}
		
		for (int i = 1; i
				<= N; i++) {
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