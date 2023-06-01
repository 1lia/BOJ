public class Main {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int[] arr = new int[N];
		int[] dp = new int[N+2];
		
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		
		if(N % 3 == 0) { //a,b 최소값에 맞추기
			for (int i = 3; i - 1 <= N; i++) {
				dp[i] = dp[i-3] + arr[i-2] - arr[i-3];
				if(i % 3 == 0) {
					dp[0] = Math.min(dp[0], dp[i]);
				} else if(i % 3 == 1) {
					dp[1] = Math.min(dp[1], dp[i]);
				} else {
					dp[2] = Math.min(dp[2], dp[i]);
				}
			}
			dp[0] = -dp[0]+1;
			dp[1] = -dp[1]+1;
			dp[2] = -dp[2]+1;
			dp[0] += (arr[0] - dp[0] - dp[1] - dp[2]);
		} else {
			for (int i = 3; i - 1 <= N; i++) {
				dp[i] = dp[i-3] + arr[i-2] - arr[i-3];
			}
			if(N % 3 == 1) {
				dp[0] = dp[N];
				dp[2] = -dp[N+1];
			} else {
				dp[2] = -dp[N];
				dp[1] = dp[N+1];
			}
			
			int a = (arr[0] - dp[0] - dp[1] - dp[2]) / 3;
			dp[0] += a; dp[1] += a; dp[2] += a;
		}
		for (int i = 3; i <= N; i++) {
			if(i % 3 == 0) {
				dp[i] += dp[0];
			} else if(i % 3 == 1) {
				dp[i] += dp[1];
			} else {
				dp[i] += dp[2];
			}	
		}	
		for (int i = 1; i <= N; i++) {
			sb.append(dp[i]).append('\n');
		}
		sb.deleteCharAt(sb.length()-1);
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