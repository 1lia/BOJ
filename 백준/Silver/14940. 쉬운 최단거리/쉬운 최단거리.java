import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<Integer>();
		Queue<Integer> q2 = new ArrayDeque<Integer>();
		int N = readInt();
		int M = readInt();
		int[][] arr = new int[N][M];
		int[][] result = new int[N][M];
		int x = 0;
		int y = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = readInt();
				if(arr[i][j] == 2) {
					x = i;
					y = j;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(result[i], -1);
		}
		result[x][y] = 0;
		q.offer(x);
		q2.offer(y);
		int[][] dxy = {{0,0,-1,1},{1,-1,0,0}};
		
		while(!q.isEmpty()) {
			x = q.poll();
			y = q2.poll();
			
			for (int i = 0; i < 4; i++) {
				int dx = x + dxy[0][i];
				int dy = y + dxy[1][i];
				
				if(0 <= dx && dx < N && 0 <= dy && dy < M) {
					if(arr[dx][dy] == 1) {
						result[dx][dy] = result[x][y] + 1;
						arr[dx][dy] = -1;
						q.offer(dx);
						q2.offer(dy);
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 0) 
					sb.append(0).append(' ');
				else
					sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
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