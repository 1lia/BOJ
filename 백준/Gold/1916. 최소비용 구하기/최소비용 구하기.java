import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static PriorityQueue<Node> q = new PriorityQueue<Node>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	public static int[] distance;
	public static int end;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int N = Integer.parseInt(bf.readLine());
		//정점개수 + 1
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		//최대값 저장 정점개수 +1 로 잡아줌 
		distance = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		int M = Integer.parseInt(bf.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			putEdge(graph , x, y , cost);
		}
		st = new StringTokenizer(bf.readLine());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(distance[end]);
	}
	
	public static void putEdge(ArrayList<ArrayList<Node>> graph , int x , int y ,int cost) {
		graph.get(x).add(new Node(y , cost));
	}
	
	public static void dijkstra(int start) {
		q.offer(new Node(start,0));
		distance[start] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			//목적지에 도착하면 빠져나가도됨
			if(node.index == end) {
				return;
			}
			//현재 거리가 더 길면 넘김
			if(distance[node.index] < node.cost) {
				continue;
			}
			
			//꺼낸노드에 연결된 그래프 탐색
			for (int i = 0; i < graph.get(node.index).size(); i++) {
				Node nextNode = graph.get(node.index).get(i);
				
				//다음노드의 최단거리보다 현재노드거리 + 다음노드까지의 거리가 작다면 
				//갱신하고 q에 넣어준다
				if(distance[nextNode.index] > node.cost + nextNode.cost) {
					distance[nextNode.index] = node.cost + nextNode.cost;
					q.offer(new Node(nextNode.index, distance[nextNode.index]));
				}
			}
		}
	}
	public static class Node{
		int index;
		int cost;
		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}
	}
}