import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	public static Queue<Integer> q;
	public static int V;
	public static int[] visited;
	public static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws Exception{
    	StringBuilder sb = new StringBuilder();
    	int K = readInt();
    	q = new ArrayDeque<Integer>();
    	
    	for (int i = 0; i < K; i++) {
			V = readInt();
			int E = readInt();
			visited = new int[V+1];
			q.clear();
			graph = new ArrayList<>();
			for (int j = 0; j <= V; j++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int j = 0; j < E; j++) {
				int u = readInt();
				int v = readInt();
				graph.get(u).add(v);
				graph.get(v).add(u);
			}
			
			if(bfs())
				sb.append("YES").append('\n');
			else
				sb.append("NO").append('\n');
		}
    	System.out.println(sb.toString());

    }
    public static boolean bfs() {
    	int v = 0;
    	int next = 0;
    	for (int i = 1; i <= V; i++) {
			if(visited[i] == 0) {
				q.offer(i);
				visited[i] = 1;
			}
			while(!q.isEmpty()) {
				v = q.poll();
				for (int j = 0; j < graph.get(v).size(); j++) {
					next = (int) graph.get(v).get(j);
					if(visited[next] == 0) {
						if(visited[v] == 1) {
							visited[next] = 2;
						} else {
							visited[next] = 1;
						}
						q.offer(next);
					}
					else if(visited[next] == visited[v]) {
						return false;
					}
				}
			}
		}
    	return true;
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