import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		int R = readInt();
		int[] arr = new int[N+1];
		int[][] map = new int[N+1][N+1];
		for (int i = 1 ; i <= N; i++) {
			arr[i] = readInt();
		}
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(map[i], 10000000);
		}
		for (int i = 0; i < R; i++) {
			int a = readInt();
			int b = readInt();
			int l = readInt();
			map[a][b] = l;
			map[b][a] = l;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
		int res = 0;
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum = 0;
			for (int j = 1; j <= N; j++) {
				if(map[i][j] <= M || i == j) {
					sum += arr[j];
				}
			}
			res = Math.max(res, sum);
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