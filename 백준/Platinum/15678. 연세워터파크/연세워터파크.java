import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception{	
		int N = (int) readInt();
		int D = (int) readInt();
		long[] arr = new long[N];
		long[] dp = new long[N];
		Deque<Pos> q = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		for (int i = 0; i < N; i++) {
			dp[i] = arr[i];
			
//			앞에서부터 범위밖값은 버림
			while (!q.isEmpty() && q.peekFirst().index < i - D) {
				q.pollFirst();
			}
			
//			dp
			if (!q.isEmpty()) {
				dp[i] = Math.max(dp[i], q.peekFirst().v + arr[i]);
			}
			
//			뒤에값을 넣기전에 dp[i]보다 작은건 버림
			while (!q.isEmpty() && q.peekLast().v < dp[i]) {
				q.pollLast();
			}
			q.offerLast(new Pos(i, dp[i]));
		}

		System.out.println(Arrays.stream(dp).max().getAsLong());
	}
	
	public static long readInt() throws Exception {
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

class Pos{
	public int index;
	public long v;

	public Pos(int key, long v) {
		this.index = key;
		this.v= v;
	}
}