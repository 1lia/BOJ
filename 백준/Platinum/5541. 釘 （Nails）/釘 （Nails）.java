public class Main {
	public static void main(String[] args) throws Exception{
		int N = readInt() + 3;
		int M = readInt();
		int result = 0;
		int[][] arr = new int[N][N];
		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();

			arr[a][b]++;
			arr[a][b+1]--;
			arr[a+c+1][b]--;
			arr[a+c+1][b+c+2]++;
			arr[a+c+2][b+1]++;
			arr[a+c+2][b+c+2]--;		
		}		
		
//		가로
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < Math.min(N, i + 4); j++) {
				arr[i][j] += arr[i][j-1];
			}
		}
		
//		세로
		for (int i = 1; i < N; i++) {
			for (int j = i; j < N; j++) {
				arr[j][i] += arr[j-1][i];
			}
		}
//		대각선
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < Math.min(N, i + 3) ; j++) {
				arr[i][j] += arr[i-1][j-1];
			}
		}
		
//		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= i; j++) {
				if(arr[i][j] != 0)
					result++;
			}
		}
		
		System.out.println(result);
	}
	
	
	public static int readInt() throws Exception {
		int val = 0;
		boolean negative = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				negative = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return negative ? -val : val;
	}
}