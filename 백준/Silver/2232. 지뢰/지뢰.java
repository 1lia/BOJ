public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		
		boolean flag = false;
		
		for (int i = 1; i < N; i++) {
			if(!flag) {
				if(arr[i-1] > arr[i]) {
					sb.append(i).append('\n');
					flag = true;
				} else if(arr[i-1] == arr[i]) {
					sb.append(i).append('\n');
				}
			} else {
				if(arr[i-1] <= arr[i]) {
					flag = false;
				}
			}
		}
		if(N != 1) {
			if(arr[N-1] >= arr[N-2]) {
				sb.append(N);
			}
		} else {
			sb.append(1);
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