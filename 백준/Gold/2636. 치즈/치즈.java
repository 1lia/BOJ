import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
    		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					map[i][j] = true;
			}
		}
    	int cnt = 0;
    	int temp = countcheese();
    	int result = 0;
    	
    	while(temp != 0) {
    		result = temp;
    		for (int i = 0; i < N; i++) {
    			Arrays.fill(visited[i], false);
			}
    		end:
    		for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!map[i][j]) {
						bfs(i , j);
						break end;
					}
				}
			}
			cnt++;	
			temp = countcheese();
    	}
    	System.out.println(cnt);
    	System.out.println(result);	
    }
    public static void bfs(int x , int y) {
    	int dxy[][] = {{1,-1,0,0},{0,0,1,-1}};
    	Pos p = null;
    	visited[x][y] = true;
    	q.offer(new Pos(x , y));
    	while(!q.isEmpty()) {
    		p = q.poll();
    		for (int i = 0; i < 4; i++) {
				x = p.x + dxy[0][i];
				y = p.y + dxy[1][i];
				if(x < N && x >= 0 && y < M && y >= 0) {
					if(!map[x][y] && !visited[x][y]) {
						visited[x][y] = true;
						q.offer(new Pos(x , y));
					} else if(map[x][y]) {
						map[x][y] = false;
						visited[x][y] = true;
					}
				}
			}
    	}
    }
    
    public static int countcheese() {
    	int cnt = 0;
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]) {
					cnt++;
				}
			}
		}
    	return cnt;
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