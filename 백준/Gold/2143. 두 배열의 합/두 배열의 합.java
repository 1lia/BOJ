import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception {
		int T = readInt();
		int N = readInt();
		HashMap<Integer, Long> map = new HashMap<>();

		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = readInt();
		}

		N = readInt();
		int[] b = new int[N];
		for (int i = 0; i < N; i++) {
			b[i] = readInt();
		}

		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
				if (map.containsKey(sum)) {
					map.put(sum, map.get(sum) + 1);
				} else {
					map.put(sum, 1L);
				}
			}
		}
		long res = 0;
		for (int i = 0; i < b.length; i++) {
			sum = 0;
			for (int j = i; j < b.length; j++) {
				sum += b[j];
				if (map.containsKey(T - sum)) {
					res += map.get(T - sum);
				}
			}
		}
		System.out.println(res);
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