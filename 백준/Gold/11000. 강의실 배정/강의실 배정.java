import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		PriorityQueue<Long> q = new PriorityQueue<>();

		int N = (int) readLong();
		T[] arr = new T[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new T(readLong(), readLong());
		}

		Comparator<T> com = new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				if (o1.s > o2.s) {
					return 1;
				} else if(o1.s < o2.s){
					return -1;
				} else {
					if(o1.e > o1.e) {
						return 1;
					} else if(o1.e > o1.e) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		};
		Arrays.sort(arr, com);
		q.offer(arr[0].e);
		for (int i = 1; i < N; i++) {
			if (q.peek() <= arr[i].s) {
				q.poll();
			}
			q.offer(arr[i].e);
		}

		System.out.println(q.size());
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

class T {
	long s;
	long e;

	public T(long s, long e) {
		this.s = s;
		this.e = e;
	}
}