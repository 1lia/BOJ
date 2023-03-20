public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[][] dp = new int[3][2];
		int[][] arr = new int[3][N];
		int flag1 = 0;
		int flag2 = 1;

		for (int i = 0; i < N; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			arr[0][i] = a;
			arr[1][i] = b;
			arr[2][i] = c;
		}
		int result = 101010101;
		for (int z = 0; z < 3; z++) {
			for (int i = 0; i < 3; i++) {
				dp[i][flag1] = 100000000;
				dp[i][flag2] = 100000000;
			}
			dp[z][flag1] = arr[z][0];

			for (int i = 1; i < N; i++) {
				dp[0][flag2] = Math.min(dp[1][flag1] + arr[0][i], dp[2][flag1] + arr[0][i]);
				dp[1][flag2] = Math.min(dp[0][flag1] + arr[1][i], dp[2][flag1] + arr[1][i]);
				dp[2][flag2] = Math.min(dp[0][flag1] + arr[2][i], dp[1][flag1] + arr[2][i]);
				flag1 = flag1 ^ flag2;
				flag2 = flag1 ^ flag2;
				flag1 = flag1 ^ flag2;
			}
			
			for (int i = 0; i < 3; i++) {
				if(z != i && result > dp[i][flag1]) {
					result = dp[i][flag1];
				}
			}
			
		}
		System.out.println(result);
	}

	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}