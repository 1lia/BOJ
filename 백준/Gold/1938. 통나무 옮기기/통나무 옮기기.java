import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Bar> q = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		boolean[][][] visit = new boolean[N][N][2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		Bar start = null;
		Bar end = null;
		int dx1 = 0;
		int dy1 = 0;
		int dx2 = 0;
		int dy2 = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'B') {
					dx1 = i-1;
					dx2 = i+1;
					if(0 <= dx1 && dx1 < N && 0 <= dx2 && dx2 < N) {
						if(map[dx1][j] == 'B' && map[dx2][j] == 'B') {
							start = new Bar(i, j, 0, 0);
						}
					}
					
					dy1 = j-1;
					dy2 = j+1;
					if(0 <= dy1 && dy1 < N && 0 <= dy2 && dy2 < N) {
						if(map[i][dy1] == 'B' && map[i][dy2] == 'B') {
							start = new Bar(i, j, 1, 0);
						}
					}
				}
				
				if(map[i][j] == 'E') {
					dx1 = i-1;
					dx2 = i+1;
					if(0 <= dx1 && dx1 < N && 0 <= dx2 && dx2 < N) {
						if(map[dx1][j] == 'E' && map[dx2][j] == 'E') {
							end = new Bar(i, j, 0, 0);
						}
					}
					
					dy1 = j-1;
					dy2 = j+1;
					if(0 <= dy1 && dy1 < N && 0 <= dy2 && dy2 < N) {
						if(map[i][dy1] == 'E' && map[i][dy2] == 'E') {
							end = new Bar(i, j, 1, 0);
						}
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'B' || map[i][j] == 'E') {
					map[i][j] = '0';
				}
			}
		}

		q.offer(start);
		visit[start.x][start.y][start.state] = true;
		
		int ckro[][] = {{-1,-1,-1,0,0,1,1,1},{-1,0,1,-1,1,-1,0,1}};
		int ckl[][] = {{-1,0,1},{-1,-1,-1}};
		int ckr[][] = {{-1,0,1},{1,1,1}};
		int cku[][] = {{-1,-1,-1},{-1,0,1}};
		int ckd[][] = {{1,1,1},{-1,0,1}};

		
		int dx = 0;
		int dy = 0;
		boolean flag = false;
		while(!q.isEmpty()) {		
			Bar bar = q.poll();
//			도착하면 끝
			if(bar.x == end.x && bar.y == end.y && bar.state == end.state) {
				System.out.println(bar.cnt);
				return;
			}
			
			flag = false;
//			회전 할수있다면 하기
			for (int i = 0; i < 8; i++) {
				dx = bar.x + ckro[0][i];
				dy = bar.y + ckro[1][i];
				if(0 <= dx && dx < N && 0 <= dy && dy < N && map[dx][dy] == '0') {
					
				} else {
					flag = true;
					break;
				}
			}
			
			if(!flag && !visit[bar.x][bar.y][bar.state ^ 1]) {
				visit[bar.x][bar.y][bar.state ^ 1] = true;
				q.offer(new Bar(bar.x, bar.y, bar.state ^ 1, bar.cnt + 1));
			}
			
//			세로
			if(bar.state == 0) {
//				왼
				if(0 <= bar.y - 1 && bar.y - 1 < N && !visit[bar.x][bar.y - 1][bar.state]) {
					flag = false;
					for (int i = 0; i < 3; i++) {
						dx = bar.x + ckl[0][i];
						dy = bar.y + ckl[1][i];
						if(0 <= dx && dx < N && 0 <= dy && dy < N && map[dx][dy] == '0') {
						
						} else {
							flag = true;
							break;
						}
					}
					if(!flag) {
						visit[bar.x][bar.y - 1][bar.state] = true;
						q.offer(new Bar(bar.x, bar.y - 1, bar.state, bar.cnt + 1));
					}
				}
		
//				오
				if(0 <= bar.y + 1 && bar.y + 1 < N && !visit[bar.x][bar.y + 1][bar.state]) {
					flag = false;
					for (int i = 0; i < 3; i++) {
						dx = bar.x + ckr[0][i];
						dy = bar.y + ckr[1][i];
						if(0 <= dx && dx < N && 0 <= dy && dy < N && map[dx][dy] == '0') {
						
						} else {
							flag = true;
							break;
						}
					}
					if(!flag) {
						visit[bar.x][bar.y + 1][bar.state] = true;
						q.offer(new Bar(bar.x, bar.y + 1, bar.state, bar.cnt + 1));
					}
				}
				
//				위
				dx = bar.x - 2;
				dy = bar.y;
				if(0 <= dx && dx < N && map[dx][dy] == '0' && !visit[bar.x - 1][bar.y][bar.state]) {
					visit[bar.x - 1][bar.y][bar.state] = true;
					q.offer(new Bar(bar.x - 1, bar.y, bar.state, bar.cnt + 1));
				}
				
//				아래
				dx = bar.x + 2;
				dy = bar.y;
				if(0 <= dx && dx < N && map[dx][dy] == '0' && !visit[bar.x + 1][bar.y][bar.state]) {
					visit[bar.x + 1][bar.y][bar.state] = true;
					q.offer(new Bar(bar.x + 1, bar.y, bar.state, bar.cnt + 1));
				}
				
			} else {
//				왼
				dx = bar.x;
				dy = bar.y - 2;
				if(0 <= dy && dy < N && map[dx][dy] == '0' && !visit[bar.x][bar.y - 1][bar.state]) {
					visit[bar.x][bar.y - 1][bar.state] = true;
					q.offer(new Bar(bar.x, bar.y - 1, bar.state, bar.cnt + 1));
				}
				
//				오
				dx = bar.x;
				dy = bar.y + 2;
				if(0 <= dy && dy < N && map[dx][dy] == '0' && !visit[bar.x][bar.y + 1][bar.state]) {
					visit[bar.x][bar.y + 1][bar.state] = true;
					q.offer(new Bar(bar.x, bar.y + 1, bar.state, bar.cnt + 1));
				}
				
//				위
				if(0 <= bar.x - 1 && bar.x - 1 < N && !visit[bar.x - 1][bar.y][bar.state]) {
					flag = false;
					for (int i = 0; i < 3; i++) {
						dx = bar.x + cku[0][i];
						dy = bar.y + cku[1][i];
						if(0 <= dx && dx < N && 0 <= dy && dy < N && map[dx][dy] == '0') {
						
						} else {
							flag = true;
							break;
						}
					}
					if(!flag) {
						visit[bar.x - 1][bar.y][bar.state] = true;
						q.offer(new Bar(bar.x - 1, bar.y, bar.state, bar.cnt + 1));
					}
				}
				
//				아래
				if(0 <= bar.x + 1 && bar.x + 1 < N && !visit[bar.x + 1][bar.y][bar.state]) {
					flag = false;
					for (int i = 0; i < 3; i++) {
						dx = bar.x + ckd[0][i];
						dy = bar.y + ckd[1][i];
						if(0 <= dx && dx < N && 0 <= dy && dy < N && map[dx][dy] == '0') {
						
						} else {
							flag = true;
							break;
						}
					}
					if(!flag) {
						visit[bar.x + 1][bar.y][bar.state] = true;
						q.offer(new Bar(bar.x + 1, bar.y, bar.state, bar.cnt + 1));
					}
				}
			}
		}
		System.out.println(0);
	}
}

class Bar{
	int x;
	int y;
	int state; //0이 세로  1이 가로
	int cnt;
	public Bar(int x, int y, int state, int cnt) {
		this.x = x;
		this.y = y;
		this.state = state;
		this.cnt = cnt;
	}
}