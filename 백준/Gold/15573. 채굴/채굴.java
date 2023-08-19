import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int K = readInt();
		int[][] arr = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = readInt();
			}
		}
		int s = 1;
		int e = 1000000;
		int m;
		int cnt;
		Queue<Pos> q = new ArrayDeque<Pos>();
		int[][] dxy = {{1,-1,0,0},{0,0,1,-1}};
		while(s < e) {
			m = (s + e) >> 1;
			cnt = 0;
			q.clear();
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
			}
			
			
			for (int i = 0; i < M; i++) {
				if(arr[0][i] <= m) {
					q.offer(new Pos(0,i));
					visit[0][i] = true;
				}
			}
			
			for (int i = 1; i < N; i++) {
				if(arr[i][0] <= m) {
					q.offer(new Pos(i,0));
					visit[i][0] = true;
				}
				
				if(arr[i][M-1] <= m) {
					q.offer(new Pos(i,M-1));
					visit[i][M-1] = true;
				}
			}
			
			while(!q.isEmpty()) {
				cnt++;
				Pos p = q.poll();
				for (int i = 0; i < 4; i++) {
					int dx = dxy[0][i] + p.x;
					int dy = dxy[1][i] + p.y;
					if(0 <= dx && dx < N && 0 <= dy && dy < M && !visit[dx][dy] && arr[dx][dy] <= m) {
						q.offer(new Pos(dx, dy));
						visit[dx][dy] = true;
					}
				}
			}
			if(cnt >= K) {
				e = m;
			} else {
				s = m + 1;
			}	
		}
		System.out.println(e);	
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

class Pos{
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}