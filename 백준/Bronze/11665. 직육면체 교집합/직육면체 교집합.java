public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[][] arr = new int[2][3];
		for (int i = 0; i < 3; i++) {
			arr[1][i] = 1001;
		}
		for (int i = 0; i < N; i++) {
			arr[0][0] = Math.max(arr[0][0], readInt());
			arr[0][1] = Math.max(arr[0][1], readInt());
			arr[0][2] = Math.max(arr[0][2], readInt());
			arr[1][0] = Math.min(arr[1][0], readInt());
			arr[1][1] = Math.min(arr[1][1], readInt());
			arr[1][2] = Math.min(arr[1][2], readInt());
		}
		int x = arr[1][0] - arr[0][0];
		int y = arr[1][1] - arr[0][1];
		int z = arr[1][2] - arr[0][2];
		if(x > 0 && y > 0 && z > 0) {
			System.out.println(x*y*z);
		} else {
			System.out.println(0);
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