import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int[][] map = new int[5][5];
		int[][] visit = new int[5][5];
		for (int i = 0; i < 5; i++) {
			Arrays.fill(visit[i], Integer.MAX_VALUE);
			for (int j = 0; j < 5; j++) {
				map[i][j] = readInt();
			}
		}
		int x = readInt();
		int y = readInt();
		visit[x][y] = 0;
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(x,y));
		int[][] dxy = {{0,0,1,-1},{1,-1,0,0}};
		int dx = 0;
		int dy = 0;
		int res = -1;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			x = p.x;
			y = p.y;
			if(map[x][y] == 1) {
				res = visit[x][y];
			}
			
			for (int i = 0; i < 4; i++) {
				dx = p.x + dxy[0][i];
				dy = p.y + dxy[1][i];
				if(0 <= dx && dx < 5 && 0 <= dy && dy < 5) {
					if(map[dx][dy] != -1 && visit[x][y] + 1 < visit[dx][dy]) {
						visit[dx][dy] = visit[x][y] + 1;
						q.add(new Pos(dx,dy));
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				dx = p.x + dxy[0][i];
				dy = p.y + dxy[1][i];
				while(0 <= dx + dxy[0][i] && dx + dxy[0][i] < 5 && 0 <= dy + dxy[1][i] && dy + dxy[1][i] < 5 && map[dx][dy] != 7 && map[dx][dy] != -1) {
					dx += dxy[0][i];
					dy += dxy[1][i];
				}
				
				if(0 <= dx && dx < 5 && 0 <= dy && dy < 5) {
					if(map[dx][dy] == -1) {
						dx -= dxy[0][i];
						dy -= dxy[1][i];
						if(visit[x][y] + 1 < visit[dx][dy]) {
							visit[dx][dy] = visit[x][y] + 1;
							q.add(new Pos(dx,dy));
						}
					} else if(visit[x][y] + 1 < visit[dx][dy]){
						visit[dx][dy] = visit[x][y] + 1;
						q.add(new Pos(dx,dy));
					}
				}
				
				
			}
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

class Pos{
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}