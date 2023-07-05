import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int X = readInt();
		int Y = readInt();
		long[] cost = new long[N+1];
		int cnt = 0;
		ArrayList<Integer>[] g = new ArrayList[N+1];
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			cost[i] = readLong();
		}
		
		for (int i = 0; i < M; i++) {
			int v = readInt();
			int u = readInt();
			g[v].add(u);
			g[u].add(v);
		}
		
		for (int i = 0; i < Y; i++) {
			int a = readInt();
			q.offer(a);
			cost[a] = -1;
		}
		
		int size = q.size();
		cnt = size;
		long mul = 1;
		while(!q.isEmpty()) {
			int t = q.poll();
			size--;	
			for (int i = 0; i < g[t].size(); i++) {
				if(cost[g[t].get(i)] != -1) {
					q.offer(g[t].get(i));
					pq.offer(cost[g[t].get(i)] * mul);
					cost[g[t].get(i)] = -1;
					cnt++;
				}
			}
			
			if(size == 0) {
				mul++;
				size = q.size();
			}
		}
		long res = 0;
		boolean flag = false;
		if(cnt != N) {
			for (int i = 0; i <= N; i++) {
				if(cost[i] > 0) {
					flag = true;
					break;
				}
			}
		} 
		if(flag){
			res = -1;
		} else{
			for (int i = 0; i < X; i++) {
				if(!pq.isEmpty())
					res += pq.poll();
				else
					break;
			}
		}
		
		System.out.println(res);
	}
	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}

	public static long readLong() throws Exception {
		long val = 0;
		long c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}
}