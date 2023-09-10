import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception {
		int A = readInt();
		int B = readInt();
		int N = readInt();
		int M = readInt();
		HashMap<Integer, Robot> hashMap = new HashMap<>();
		int[][] map = new int[A+1][B+1];
		for (int i = 1; i <= N; i++) {
			int x = readInt();
			int y = readInt();
			int p = readInt();
			hashMap.put(i, new Robot(x, y, pos(p)));
			map[x][y] = 1;
		}

		int[][] dxy = {{-1,0,1,0},{0,1,0,-1}};
		for (int i = 0; i < M; i++) {
			int n = readInt();
			Robot r = hashMap.get(n);
			int q = readInt();
			int cnt = readInt();
			if(q == 28) {
				r.pos = ((r.pos + 4) - (cnt % 4)) % 4;
			} else if(q == 34) {
				r.pos = (r.pos + cnt) % 4;
			} else {
				int x = r.x;
				int y = r.y;
				map[x][y] = 0;
				while(cnt-->0) {
					int dx = x + dxy[0][r.pos];
					int dy = y + dxy[1][r.pos];
					if(0 < dx && dx <= A && 0 < dy && dy <= B) {
						if(map[dx][dy] == 1) {
							for (int j = 1; j <= N; j++) {
								Robot t = hashMap.get(j);
								if(t.x == dx && t.y == dy) {
									System.out.println("Robot " + n + " crashes into robot " + j);
									return;
								}
							}
						}
					} else {
						System.out.println("Robot " + n + " crashes into the wall");
						return;
					}
					x = dx;
					y = dy;
				}
				map[x][y] = 1;
				hashMap.put(n , new Robot(x, y, r.pos));
			}
		}
		System.out.println("OK");
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
	
	public static int pos(int n) {
		switch (n) {
			case 30:
				return 1;
			case 21:
				return 2;
			case 35:
				return 3;
			case 39:
				return 0;
		}
		return -1;
	}
}

class Robot{
	int x;
	int y;
	int pos;
	public Robot(int x, int y ,int pos) {
		this.x = x;
		this.y = y;
		this.pos = pos;
	}
}