import java.util.ArrayList;

public class Main {
	public static ArrayList<Integer>[] g;
	public static Long[] arr;
	public static void main(String[] args) throws Exception {
		int N = readInt();
		arr = new Long[N];
		g = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			g[i] = new ArrayList<>();
		}
		for (int i = 1; i < N; i++) {
			g[readInt()].add(readInt());
		}
		for (int i = 0; i < N; i++) {
			arr[i] = readLong();
		}
		System.out.println(dfs(0));
	}
	
	public static long dfs(int v) {
		long sum = arr[v];
		for (int i = 0; i < g[v].size(); i++) {
			long t = dfs(g[v].get(i));
			if(t > 0) {
				sum += t;
			}
		}
		return sum;
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