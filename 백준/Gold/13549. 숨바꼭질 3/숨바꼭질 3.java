import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception {
		int[] visit = new int[200001];
		Arrays.fill(visit, Integer.MAX_VALUE);
		int N = readInt();
		int K = readInt();
		Deque<Integer> q = new ArrayDeque<Integer>();
		q.offer(N);
		visit[N] = 0;
		while(!q.isEmpty()) {
			int t = q.poll();
			int p = t;

			while((p << 1) < 150000 && p != 0) {
				p <<= 1;
				if(visit[p] > visit[t]) {
					visit[p] = visit[p>>1];
					q.addFirst(p);
				}
			}
			
			if(t > 0 && visit[t-1] > visit[t] + 1) {
				visit[t-1] = visit[t] + 1;
				q.offer(t-1);
			}
			
			if(t < 100000 && visit[t+1] > visit[t] + 1) {
				visit[t+1] = visit[t] + 1;
				q.offer(t+1);
			}

		}
		System.out.println(visit[K]);
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
}