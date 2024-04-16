import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt() - 1;
		int res = 0;
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int max = readInt();
			int cnt = readInt();
			if(max >= cnt) {
				res++;
			} else {
				q.offer(max);
			}
		}
		int people = 0;
		while(!q.isEmpty()) {
			people += M;
			res++;
			q.poll();
			while(!q.isEmpty() && q.peek() <= people) {
				people -= q.poll();
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
}