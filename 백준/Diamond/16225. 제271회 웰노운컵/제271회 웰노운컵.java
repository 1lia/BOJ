import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{
	public static void main(String[] args) throws Exception {	
		PriorityQueue<Pos> q = new PriorityQueue<>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				if(o2.x < o1.x)
					return -1;
				return 1;
			}
		});
		int N = (int) readLong();
		Pos[] pos = new Pos[N];
		for (int i = 0; i < N; i++) {
			pos[i] = new Pos(readLong());
		}
		for (int i = 0; i < N; i++) {
			pos[i].y = readLong();
		}
		Arrays.sort(pos);
		long result = pos[0].x;
		for (int i = 1 ; i + 1 < N ; i+=2) {
			q.offer(pos[i]);
			q.offer(pos[i+1]);
			Pos p = q.poll();
			result += p.x;
		}
		System.out.println(result);
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

class Pos implements Comparable<Pos>{
	long x;
	long y;
	
	public Pos(long x) {
		this.x = x;
	}
	@Override
	public int compareTo(Pos o) {
		if(this.y < o.y)
			return -1;
		return 1;
	}
	
}