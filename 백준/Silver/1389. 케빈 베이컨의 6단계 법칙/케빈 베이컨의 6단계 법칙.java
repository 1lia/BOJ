public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();

		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j)
					map[i][j] = 10000000;
			}
		}

		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			map[a][b] = 1;
			map[b][a] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}

		int min = 10000000;
		int result = 0;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += map[i][j];
			}

			if (min > sum) {
				result = i;
				min = sum;
			}
		}
		System.out.println(result);
	}
    public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while(c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if(flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);
		
		if(flag)
			return -val;	
		return val;
	}
}