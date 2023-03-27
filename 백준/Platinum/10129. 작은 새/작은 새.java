import java.util.ArrayDeque;
import java.util.Deque;

public class Main {	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		Deque<Pos> q = new ArrayDeque<Pos>();
		int N = readInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		
		int M = readInt();
		for (int i = 0; i < M; i++) {
			q.clear();
			int state = 0;
			int k = readInt();
//			새dp
			q.offer(new Pos(0 , arr[0] , 0));
			for (int j = 1; j < N; j++) {
				
//				못넘어오는 거리면 지우기
				while(!q.isEmpty() && q.peekFirst().index < j - k) {
					q.pollFirst();
				}
				
//				더높으면 
				if(q.peekFirst().high <= arr[j])
					state = q.peekFirst().count + 1;
				else
					state = q.peekFirst().count;
				
//				필요없는것 제거
				while(!q.isEmpty() && (q.peekLast().count > state  || (q.peekLast().count == state && q.peekLast().high <= arr[j]))) {
					q.pollLast();
				}
				q.offer(new Pos(j , arr[j] , state));			
			}
			sb.append(state).append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());

	}
	
	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
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
	int index;
	int high;
	int count;
	public Pos(int index, int high, int count) {
		this.index = index;
		this.high = high;
		this.count = count;
	}	
}