import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		Deque<Pos> q = new ArrayDeque<Pos>();
		int N = readInt();
		int size = (N << 1) - 1;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = readInt();
			Pos p = new Pos(arr[i] , (arr[i] & 1));
			p.bit[i / 60] = 1L << (i % 60);
			q.offer(p);
		}
		int t = 1;
		int k = 0;
		while(t < N) {
			k++;
			t <<= 1;
		}

		int d = 4;
		for (int i = 0; i < k; i++) {
			int cnt = (q.size() >> 1);
			for (int j = 0; j < cnt; j++) {
				Pos p1 = q.pollFirst();
				Pos p2 = q.pollFirst();
				Pos p3 = q.pollFirst();
				if(p1.n == p2.n) {
					q.offerFirst(p3);
					sum(p1,p2,d);
					q.offerLast(p1);
				} else if(p1.n == p3.n) {
					q.offerFirst(p2);
					sum(p1,p3,d);
					q.offerLast(p1);
				} else {
					q.offerFirst(p1);
					sum(p2,p3,d);
					q.offerLast(p2);
				}
			}
			d <<= 1;
			q.pollFirst();
		}

		Pos p = q.poll();
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 60; j++) {
				if(((1L << j) & p.bit[i]) != 0) {
					sb.append(arr[i * 60 + j]).append(' ');
				}
			}
		}
		System.out.println(sb);
	}
	public static void sum(Pos p1 , Pos p2 , int d) {
		p1.sum += p2.sum;
		p1.n = p1.sum % d;
		for (int i = 0; i < 40; i++) {
			p1.bit[i] |= p2.bit[i]; 
		}
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
	int sum;
	int n;
	long[] bit;
	public Pos(int sum , int n) {
		this.sum = sum;
		this.n = n;
		this.bit = new long[40];
	}
}