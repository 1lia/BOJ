import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int res = 0;
		int t = 0;
		int[] arr = new int[5];
		for (int i = 0; i < N; i++) {
			t = 0;
			int a = readInt();
			int b = readInt();
			t += Math.max(a, b);
			for (int j = 0; j < 5; j++) {
				arr[j] = readInt();
			}
			Arrays.sort(arr);
			t += (arr[4] + arr[3]);
			res = Math.max(res, t);
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