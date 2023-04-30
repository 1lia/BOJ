import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Deque<Pos> q = new ArrayDeque<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		int[][] visit = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		int[][] dxy = {{1,-1,0,0} ,{ 0 , 0 , 1 , -1 }};
		q.offer(new Pos(0 , 0));
		visit[0][0] = 0;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int i = 0; i < 4; i++) {
				int dx = p.x + dxy[0][i];
				int dy = p.y + dxy[1][i];
				if(0 <= dx && dx < M && 0 <= dy && dy < N) {
					if(visit[p.x][p.y] + map[dx][dy] < visit[dx][dy]) {
						visit[dx][dy] =  visit[p.x][p.y] + map[dx][dy];
						if(dx + 1 == M && dy + 1 == N) {
							System.out.println(visit[dx][dy]);
							return;
						}
						if(map[dx][dy] == 1) {
							q.offer(new Pos(dx , dy));
						} else {
							q.offerFirst(new Pos(dx , dy));
						}
					}
				}
			}
		}
		System.out.println(0);
	}
}

class Pos {
	int x = 0;
	int y = 0;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}