import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int T = readInt();
		StringBuilder sb = new StringBuilder();
		for (int z = 0; z < T; z++) {
			int N = readInt();
			int[] team = new int[N+1];
			int[][] graph = new int[N+1][N+1];
			int[] indegree = new int[N+1];
			
			for (int i = 1; i <= N; i++) {
				team[i] = readInt();
				for (int j = 1; j < i; j++) {
					graph[team[j]][team[i]] = 1;
				}
			}
			
			int M = readInt();
			
//			뒤바꿔줌 
			for (int i = 0; i < M; i++) {
				int a = readInt();
				int b = readInt();
				
				graph[a][b] = graph[a][b] ^ graph[b][a];
				graph[b][a] = graph[a][b] ^ graph[b][a];
				graph[a][b] = graph[a][b] ^ graph[b][a];	
			}
			
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					if(graph[i][j] == 1) {
						indegree[j]++;
					}
				}
			}
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i < N+1; i++) {
				if(indegree[i] == 0) {
					q.offer(i);
				}
			}
			StringBuilder tsb = new StringBuilder();
			int cnt = 0;
			while(!q.isEmpty()) {
				int v = q.poll();
				tsb.append(v).append(' ');
				cnt++;
				for (int i = 1; i < N+1; i++) {
					if(graph[v][i] == 1) {
						indegree[i]--;
						if(indegree[i] == 0) {
							q.add(i);
						}
					}
				}
				
				if(q.size() > 1 && cnt != N){
					sb.append('?').append('\n');
					break;
				}
			}
			
			if(cnt == N) {
				sb.append(tsb).append('\n');
			} else if(q.size() == 0) {
				sb.append("IMPOSSIBLE").append('\n');
			}
		}
		System.out.println(sb.toString());
	}
	
	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}
