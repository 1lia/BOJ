public class Main {
	public static void main(String[] args) throws Exception {
		int MOD = 1999;
		int N = readInt();
		int M = readInt();
		int[] arr = new int[M+1];
		arr[1] = 1;
		if(N == 1) {
			arr[M] = 1;
		}else if(N > M) {		
			for (int i = 2; i <= M; i++) {
				arr[i] = (arr[i-1] + arr[i-1]) % MOD;
			}
		} else {
			for (int i = 2; i < N; i++) {
				arr[i] = (arr[i-1] + arr[i-1]) % MOD;
			}
			arr[N] = (4 * arr[N-1] - 1) % MOD;;
			
			for (int i = N + 1; i <= M; i++) {
				arr[i] = ((arr[N-1] * 2)) * arr[i-N] % MOD;;

				for (int j = 1; j < N; j++) {
					arr[i] = (arr[i] + arr[i-j]) % MOD;;
				} 
			}
		}

		System.out.println(arr[M] % MOD);
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