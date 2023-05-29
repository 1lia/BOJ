import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt();
		Deque<Pos> q = new ArrayDeque<>();
		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		for (int i = 1; i < M + M; i++) {
			while (!q.isEmpty() && q.peekLast().n <= arr[i]) {
				q.pollLast();
			}
			q.offerLast(new Pos(arr[i], i));
		}
		sb.append(q.peekFirst().n).append(' ');
		for (int i = M + M; i <= N; i++) {
			while (!q.isEmpty() && q. peekLast().n <= arr[i]) {
				q.pollLast();
			}
			q.offerLast(new Pos(arr[i], i));
			while (!q.isEmpty() && q.peekFirst().idx <= i - M - M + 1) {
				q.pollFirst();
			}
			sb.append(q.peekFirst().n).append(' ');
		}
		System.out.println(sb);
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

class Pos{
	int n;
	int idx;
	public Pos(int n, int idx) {
		this.n = n;
		this.idx = idx;
	}
}