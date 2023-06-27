import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		long A = readLong();
		long B = readLong();
		int res = -1;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(A , 1));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(p.n == B) {
				res = p.cnt;
				break;
			}
			
			if(p.n * 10 + 1 <= B) {
				q.offer(new Pos(p.n * 10 + 1 , p.cnt + 1));
			}
			
			if(p.n * 2 <= B) {
				q.offer(new Pos(p.n * 2 , p.cnt + 1));
			}
		}
		System.out.println(res);
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

class Pos{
	long n;
	int cnt;
	public Pos(long n, int cnt) {
		this.n = n;
		this.cnt = cnt;
	}
}