import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		Queue<Integer> q = new ArrayDeque<>();
		int N = readInt();
		int[] arr = new int[N+1];
		int res = (1 << (N+1)) - 2;
		boolean[] visit = new boolean[1 << (N + 1)];
		int state = 0;
		for (int i = 1; i <= N; i++) {
			state |= (readInt() << i);
		}
		for (int i = 1; i <= N; i++) {
			int c = readInt();
			arr[i] |= (1 << i);
			for (int j = 0; j < c; j++) {
				arr[i] |= (1 << readInt());
			}
		}
		q.offer(state);
		int cnt = 0;
		int check = 1;
		while(!q.isEmpty()) {
			int t = q.poll();
			
			if(t == res) {
				System.out.println(cnt);
				return;
			}

			for (int i = 1; i <= N; i++) {
				if(((1 << i) | t) != t && !visit[t ^ arr[i]]) {
					q.offer(t ^ arr[i]);
					visit[t ^ arr[i]] = true;
				}
			}
			
			check--;
			if(check == 0) {
				check = q.size();
				cnt++;
			}
		}
		System.out.println(-1);
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