import java.util.ArrayList;

public class Main {
	public static ArrayList<Integer>[] arr;
	public static int[] idx;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int C = readInt();
		idx = new int[N + 1];
		arr = new ArrayList[C+1];
		for (int i = 0; i <= C; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			idx[i] = readInt();
			arr[idx[i]].add(i);
		}
		int Q = readInt();
		for (int i = 0; i < Q; i++) {
			int a = readInt();
			int b = readInt();
			boolean flag = false;
			int color = 0;
			for (int j = 0; j < 20; j++) {
				int t = rand(a,b);
				int cnt = upper(a , b , t) - lower(a , b , t) + 1;
				if(cnt > ((b - a + 1) >> 1)) {
					flag = true;
					color = t;
					break;
				}
			}
			if(flag)
				sb.append("yes ").append(color);
			else
				sb.append("no");
			sb.append('\n');	
		}
		System.out.println(sb);
	}
	public static int lower(int a , int b , int t) {
		int s = 0;
		int e = arr[t].size() - 1;
		int m = 0;
		while(s < e) {
			m = ((s + e) >> 1);
			int p = arr[t].get(m);
			if(a <= p) {
				e = m;
			} else {
				s = m + 1;
			}
			
		}
		return s;
	}
	
	public static int upper(int a , int b , int t) {
		int s = 0;
		int e = arr[t].size() - 1;
		int m = 0;
		while(s < e) {
			m = ((s + e) >> 1) + 1;
			int p = arr[t].get(m);
			if(p <= b) {
				s = m;
			} else {
				e = m - 1;
			}
		}
		return e;
	}
	
	public static int rand(int l , int r) {		
		return idx[(int) (Math.random() * (r - l + 1)) + l];
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