import java.util.Arrays;

public class Main {
	public static StringBuilder sb;
	public static int[] arr;
	public static int N;
	public static int M;
	public static void main(String[] args) throws Exception {
		N = readInt();
		M = readInt();
		sb = new StringBuilder();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		Arrays.sort(arr);
		dfs(0,0 , null);
		System.out.println(sb);

	}
	public static void dfs(int cnt , int n , StringBuilder s) {
		if(cnt == M) {
			sb.append(s).append('\n');
			return;
		}

		for (int j = 0; j < N; j++) {
			if((n | (1 << j)) != n) {
				StringBuilder ss = new StringBuilder();
				if(cnt != 0)
					ss.append(s);	
				ss.append(arr[j]).append(' ');
				dfs(cnt+1 , n | 1 << j , ss);
			}
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