import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static ArrayList<Node>[] g;
	public static PriorityQueue<Node> q = new PriorityQueue<>();
	public static long[] distance;
	public static int end;
	public static long size;

	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int start = readInt();
		end = readInt();
		size = readlong();
		g = new ArrayList[N + 1];
		distance = new long[N + 1];

		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int s = readInt();
			int e = readInt();
			long cost = readlong();
			g[s].add(new Node(e, cost));
			g[e].add(new Node(s, cost));
		}

		long s = 0;
		long e = 21;
		long m = 0;
		long temp = 0;
		while (s != e) {
			Arrays.fill(distance, Long.MAX_VALUE);
			q.clear();
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

	public static long dijkstra(int start, long maxcost) {
		distance[start] = 0;
		q.offer(new Node(start , 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
//			System.out.println(node.cost);
			if(node.v == end)
				return distance[end];
			// 꺼낸노드에 연결된 그래프 탐색
			for (int i = 0; i < g[node.v].size(); i++) {
				Node nextNode = g[node.v].get(i);
				
				// 다음노드의 최단거리보다 현재노드거리 + 다음노드까지의 거리가 작다면
				if (nextNode.cost <= maxcost) {
					if (distance[nextNode.v] > node.cost + nextNode.cost) {
						distance[nextNode.v] = node.cost + nextNode.cost;
						if(distance[nextNode.v] <= size)
							q.offer(new Node(nextNode.v , distance[nextNode.v]));
					}
				}
			}
		}
		return distance[end];
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
	
	public static long readlong() throws Exception {
		long val = 0;
		boolean flag = false;
		do {
			long c = System.in.read();
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

class Node implements Comparable<Node>{
	int v;
	long cost;

	public Node(int v, long cost) {
		this.v = v;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		if(this.cost < o.cost) {
			return -1;
		} else if(this.cost == o.cost) {
			return 0;
		} else {
			return 1;
		}
	}
}