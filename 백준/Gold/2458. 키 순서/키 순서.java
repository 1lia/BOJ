import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();

		int N = readInt();
		int M = readInt();
		ArrayList<Integer>[] graph1 = new ArrayList[N + 1];
		ArrayList<Integer>[] graph2 = new ArrayList[N + 1];
		boolean[] visit = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			graph1[a].add(b);
			graph2[b].add(a);
		}

		int result = 0;
		for (int p = 1; p < visit.length; p++) {
			q.offer(p);
			Arrays.fill(visit, false);
			int count = 0;
			while (!q.isEmpty()) {
				int node = q.poll();
				count++;
				for (int i = 0; i < graph1[node].size(); i++) {
					int newnode = graph1[node].get(i);
					if (!visit[newnode]) {
						visit[newnode] = true;
						q.offer(newnode);
					}
				}
			}
			q.offer(p);
			while (!q.isEmpty()) {
				int node = q.poll();
				count++;
				for (int i = 0; i < graph2[node].size(); i++) {
					int newnode = graph2[node].get(i);
					if (!visit[newnode]) {
						visit[newnode] = true;
						q.offer(newnode);
					}
				}
			}
			if (count == N + 1) {
				result++;
			}
		}
		System.out.println(result);
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