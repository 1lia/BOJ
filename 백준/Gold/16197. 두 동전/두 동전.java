import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Pos> q = new ArrayDeque<Pos>();
		char[][] arr = new char[N][M];
		int x1 = -1 , x2 = -1 , y1 = -1 , y2 = -1;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j] == 'o') {
					if(x1 == -1) {
						x1 = i;
						y1 = j;
					} else {
						x2 = i;
						y2 = j;
					}
					arr[i][j] = '.';
				}
			}
		}
		int[][] dxy = {{1,-1,0,0},{0,0,1,-1}};
		q.offer(new Pos(x1,y1,x2,y2,0));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(p.cnt == 10) {
				System.out.println(-1);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int dx1 = p.x1 + dxy[0][i];
				int dy1 = p.y1 + dxy[1][i];
				int dx2 = p.x2 + dxy[0][i];
				int dy2 = p.y2 + dxy[1][i];
				if(0 <= dx1 && dx1 < N && 0 <= dy1 && dy1 < M && 0 <= dx2 && dx2 < N && 0 <= dy2 && dy2 < M) {
					if(arr[dx1][dy1] == '#') {
						dx1 = p.x1;
						dy1 = p.y1;
					}
					if(arr[dx2][dy2] == '#') {
						dx2 = p.x2;
						dy2 = p.y2;
					}
					q.offer(new Pos(dx1,dy1,dx2,dy2,p.cnt + 1));
				} else if(0 <= dx1 && dx1 < N && 0 <= dy1 && dy1 < M){
					System.out.println(p.cnt + 1);
					return;
				} else if(0 <= dx2 && dx2 < N && 0 <= dy2 && dy2 < M) {
					System.out.println(p.cnt + 1);
					return;
				}
			}
		}
	}
}

class Pos{
	public Pos(int x1, int y1, int x2, int y2, int cnt) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.cnt = cnt;
	}
	int x1;
	int y1;
	int x2;
	int y2;
	int cnt;
}