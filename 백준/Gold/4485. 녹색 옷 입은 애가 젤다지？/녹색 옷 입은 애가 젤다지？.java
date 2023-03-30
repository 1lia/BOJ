import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		PriorityQueue<Node> q = new PriorityQueue<>(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[][] dxy = {{1,-1,0,0},{0,0,1,-1}};
		int dx = 0;
		int dy = 0;
		Node node = null;
		int cnt = 1;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			int[][] arr = new int[N][N];
			int[][] distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			q.clear();
			q.offer(new Node(0,0,arr[0][0]));
			
			while(!q.isEmpty()) {
				node = q.poll();
				if(node.x == N-1 && node.y == N-1) {
					break;
				}
				for (int i = 0; i < 4; i++) {
					dx = node.x + dxy[0][i];
					dy = node.y + dxy[1][i];
					if(dx < N && dx >= 0 && dy < N && dy >= 0) {
						if(node.cost + arr[dx][dy] < distance[dx][dy]) {
							distance[dx][dy] = node.cost + arr[dx][dy];
							q.offer(new Node(dx,dy,node.cost + arr[dx][dy]));
						}
					}
				}
			}
			sb.append("Problem ").append(cnt++).append(": ").append(node.cost).append('\n');
		}
		System.out.println(sb.toString());
		
	}
}

class Node implements Comparable<Node>{
	int x;
	int y;
	int cost;
	public Node(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}