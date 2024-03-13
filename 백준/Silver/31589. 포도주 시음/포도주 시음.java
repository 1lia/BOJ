import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {	
		int N = (int) readLong();
		int K = (int) (readLong() - 1) >> 1;
		
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readLong();
		}
		Arrays.sort(arr);
		long res = arr[N - 1];
		for (int i = 0; i < K; i++) {
			res += arr[N - 2 - i] - arr[i];
		}
		System.out.println(res);
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