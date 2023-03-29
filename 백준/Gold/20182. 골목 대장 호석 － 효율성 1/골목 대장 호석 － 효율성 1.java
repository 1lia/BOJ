import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Node>[] g;
	public static PriorityQueue<Integer> q = new PriorityQueue<>();
	public static int[] distance;
	public static int end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		g = new ArrayList[N + 1];
		distance = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			g[s].add(new Node(e, cost));
			g[e].add(new Node(s, cost));
		}

		int s = 0;
		int e = 21;
		int m = 0;
		int temp = 0;
		while (s != e) {
			Arrays.fill(distance, Integer.MAX_VALUE);
			m = (s + e) >> 1;
			temp = dijkstra(start, m);
			if (temp <= size) {
				e = m;
			} else {
				s = m + 1;
			}
		}
		
		if(e == 21)
			System.out.println(-1);
		else
			System.out.println(e);
	}

	public static int dijkstra(int start, int maxcost) {
		distance[start] = 0;
		q.offer(start);

		while (!q.isEmpty()) {
			int v = q.poll();

			// 꺼낸노드에 연결된 그래프 탐색
			for (int i = 0; i < g[v].size(); i++) {
				Node nextNode = g[v].get(i);

				// 다음노드의 최단거리보다 현재노드거리 + 다음노드까지의 거리가 작다면
				if (nextNode.cost <= maxcost) {
					if (distance[nextNode.v] > distance[v] + nextNode.cost) {
						distance[nextNode.v] = distance[v] + nextNode.cost;
						q.offer(nextNode.v);
					}
				}
			}
		}
		return distance[end];
	}
}

class Node {
	int v;
	int cost;

	public Node(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
}