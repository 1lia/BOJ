import java.util.HashMap;

public class Main {
	public static int[] arr;
	public static int N, S, mid;
	public static long res;
	public static HashMap<Integer, Integer> map;
	public static void main(String[] args) throws Exception {
		N = readInt();
		S = readInt();
		mid = N / 2;
		arr = new int[N];
		map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		map.put(0, 1);
		dfs(0, 0);
		dfs2(mid,0);
		if(map.containsKey(S)) {
			res += map.get(S);
			if(S == 0)
				res--;
		}
		System.out.println(res);
	}

	public static void dfs(int c, int sum) {
		if (c == mid)
			return;

		if (map.containsKey(sum + arr[c])) {
			map.put(sum + arr[c], map.get(sum + arr[c]) + 1);
		} else {
			map.put(sum + arr[c], 1);
		}
		dfs(c + 1, sum + arr[c]);
		dfs(c + 1, sum);
	}

	public static void dfs2(int c, int sum) {
		if (N == c)
			return;

		if (map.containsKey(S - sum - arr[c])) {
			res += map.get(S - sum - arr[c]);
		}
		
		dfs2(c + 1, sum + arr[c]);
		dfs2(c + 1, sum);
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