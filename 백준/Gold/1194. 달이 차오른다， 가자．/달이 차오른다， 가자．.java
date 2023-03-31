import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	char[][] map = new char[N][M];
    	boolean[][][] visit = new boolean[N][M][1 << 6];
    	Queue<Pos> q = new ArrayDeque<>();
    	
    	for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '0') {
					q.offer(new Pos(i,j,0,0));
					map[i][j] = '.';
				}
			}
		}
    	int[][] dxy = {{1,-1,0,0},{0,0,1,-1}};
    	Pos pos;
    	int dx = 0;
    	int dy = 0;
    	while(!q.isEmpty()) {
    		pos = q.poll();
//    		System.out.println(pos.x + " " + pos.y + " " + pos.key + " " + pos.count);
    		for (int i = 0; i < 4; i++) {
				dx = pos.x + dxy[0][i];
				dy = pos.y + dxy[1][i];
				if(dx >= 0 && dx < N && dy >= 0 && dy < M) {
//					아직 안갔으면
					if(!visit[dx][dy][pos.key]) {
						visit[dx][dy][pos.key] = true;
						if(map[dx][dy] == '.') {
//							이동
							q.offer(new Pos(dx,dy,pos.key,pos.count + 1));
						} else if(map[dx][dy] >= 'a' && map[dx][dy] <= 'f') {
//							열쇠방이면
							q.offer(new Pos(dx,dy,pos.key | (1 << (map[dx][dy] - 'a')) , pos.count + 1));		
//							열쇠가 있다면 문따고들어감
						} else if(map[dx][dy] >= 'A' && map[dx][dy] <= 'F' && (((1 << (map[dx][dy] - 'A')) & pos.key) != 0)) {
							q.offer(new Pos(dx,dy,pos.key,pos.count + 1));
						} else if(map[dx][dy] == '1') {
							System.out.println(pos.count + 1);
							return;
						}
					}
				}	
			}
    	}
    	System.out.println(-1);
    }
}

class Pos{
	int x;
	int y;
	int key;
	int count;
	public Pos(int x, int y, int key , int count) {
		this.x = x;
		this.y = y;
		this.key = key;
		this.count = count;
	}
}