import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {	
		int N = readInt();
		PriorityQueue<Integer> q1 = new PriorityQueue<>();
		PriorityQueue<Integer> q2 = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			q1.offer(readInt());
		}
		int result = 0;
		int sum = 0;
		while(!q1.isEmpty() || !q2.isEmpty()) {
			sum = 0;
			while(!q1.isEmpty()) {
				int a = q1.poll();
				if(a >= sum) {
					sum++;
				} else {
					q2.offer(a);
				}
			}
			
			if(sum != 0)
				result++;
			
			sum = 0;
			while(!q2.isEmpty()) {
				int a = q2.poll();
				if(a >= sum) {
					sum++;
				} else {
					q1.offer(a);
				}
			}
			
			if(sum != 0)
				result++;
		}
		
		System.out.println(result);
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