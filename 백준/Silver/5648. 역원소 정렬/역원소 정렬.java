import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = reverse();
		}
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append('\n');
		}
		System.out.println(sb);
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

	public static long reverse() throws Exception {
		long val = 0;
		long t = 1;
		long c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		do {
			val = val + (c - 48) * t;
			t *= 10;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		return val;
	}
}