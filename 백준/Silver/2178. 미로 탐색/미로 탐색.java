import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static boolean[][] map;
	public static boolean[][] visited;
	public static int N;
	public static int M;
	public static Queue<Pos> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	map = new boolean[N][M];
    	visited = new boolean[N][M];
    	
    	for (int i = 0; i < N; i++) {
    		String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if(s.charAt(j) == '1')
					map[i][j] = true;	
			}
		}
    	bfs();
    }
    
    public static void bfs() {
    	int dxy[][] = {{1,-1,0,0},{0,0,1,-1}};
    	Pos p = null;
    	int dx = 0;
    	int dy = 0;
    	visited[0][0] = true;
    	q.offer(new Pos(0 , 0 , 1));
    	while(!q.isEmpty()) {
    		p = q.poll();
    		if(p.x == N-1 && p.y == M-1) {
    			System.out.println(p.cnt);
    			return;
    		}
    		for (int i = 0; i < 4; i++) {
				dx = p.x + dxy[0][i];
				dy = p.y + dxy[1][i];
				if(dx < N && dx >= 0 && dy < M && dy >= 0 && map[dx][dy] && !visited[dx][dy]) {
					visited[dx][dy] = true;
					q.offer(new Pos(dx , dy , p.cnt+1));
				}
			}
    	}
    }
}

class Pos{
	int x;
	int y;
	int cnt;
	public Pos(int x, int y , int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}