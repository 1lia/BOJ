import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static ArrayList<Node>[] g;
	public static Queue<Integer> q = new ArrayDeque<>();
	public static int[] distance;
	public static int end;
	public static int size;

	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int start = readInt();
		end = readInt();
		size = readInt();
		g = new ArrayList[N + 1];
		distance = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int s = readInt();
			int e = readInt();
			int cost = readInt();
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

class Node {
	int v;
	int cost;

	public Node(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
}