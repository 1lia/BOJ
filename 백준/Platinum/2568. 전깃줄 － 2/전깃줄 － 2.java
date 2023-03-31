import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		Pos[] arr = new Pos[N];
		int[] dp = new int[N];
		int[] track = new int[500001];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new Pos(readInt() , readInt());
		}
		
		Arrays.sort(arr);
		
		int index = 0;
		dp[0] = arr[0].y;
		for (int i = 1; i < N; i++) {	
//			맨뒤값보다 크면 맨뒤에넣어줌
			if(dp[index] <= arr[i].y) {
				track[arr[i].y] = dp[index];    //y전에 값을 저장해줌
				dp[++index] = arr[i].y;        
			} else {
				int temp = upper_bound(dp , arr[i].y , index);
				if(temp != 0)
					track[arr[i].y] = dp[temp - 1];
				dp[temp] = arr[i].y;
			}
		}
		sb.append(N-index-1).append('\n');
		
		index = dp[index];
		Stack<Integer> s = new Stack<>();
		
		while(true) {
			if(index == 0)
				break;
			s.push(index);
			index = track[index];
		}
		
		for (int i = 0; i < N; i++) {
			if(!s.isEmpty() && s.peek() == arr[i].y) {
				s.pop();
				continue;
			}
			sb.append(arr[i].x).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	
	private static int upper_bound(int[] dp, int y , int index) {
		
		int s = 0;
		int e = index;
		int m = 0;
		while(s != e) {
			m = ((s + e) >> 1);
			
			if(dp[m] < y) {
				s = m + 1;
			} else {
				e = m;
			}
			
		}
		return s;
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

class Pos implements Comparable<Pos>{
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Pos o) {
		return this.x - o.x;
	}
}