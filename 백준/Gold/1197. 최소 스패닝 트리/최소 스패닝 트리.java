import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = readInt();
		int E = readInt();

		PriorityQueue<Node> pq = new PriorityQueue<>();
		List<List<Node>> g = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			g.add(new ArrayList<Node>());
		}
		for (int i = 0; i < E; i++) {
			int A = readInt();
			int B = readInt();
			int C = readInt();
			putEdge(g,A,B,C);
		}
		
		boolean[] visit = new boolean[V+1];
		int result = 0;
		int cnt = 1;
		
		
//		처음 방문체크
		visit[1] = true;
		Node node = null;
		int[] visitmin = new int[V+1];
		for (int i = 0; i < g.get(1).size(); i++) {
			pq.add(g.get(1).get(i));
		}

		while(!pq.isEmpty()) {
			node = pq.poll();
			if(!visit[node.end]) {
				visit[node.end] = true;
				cnt++;
				result += node.w;
				for (int i = 0; i < g.get(node.end).size(); i++) {
					
					if(!visit[g.get(node.end).get(i).end] && (visitmin[g.get(node.end).get(i).end] == 0 || visitmin[g.get(node.end).get(i).end] > g.get(node.end).get(i).w)) {
						visitmin[g.get(node.end).get(i).end] = g.get(node.end).get(i).w;
						pq.add(g.get(node.end).get(i));
					}
						
				}
			}
			if(cnt == V)
				break;
		}
		System.out.println(result);
	}
	
	public static void putEdge(List<List<Node>> g , int x , int y , int w) {
		g.get(x).add(new Node(y,w));
		g.get(y).add(new Node(x,w));
	}
	
	public static int readInt() throws Exception {
        int val = 0;
        boolean negative = false;
        do {
            int c = System.in.read();
            if (c == '-') {
                negative = true;
                continue;
            }
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return negative ? -val : val;
    }
}
class Node implements Comparable<Node>{
	int end;
	int w;
	
	Node(int end , int w){
		this.end = end;
		this.w = w;
	}
	@Override
	public int compareTo(Node o) {
		if(this.w < o.w)
			return -1;
		return 1;
	}
}