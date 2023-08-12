import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int K = readInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		Arrays.sort(arr);
		int s = 0;
		int e = N-1;
		int cnt = 0;
		
		while(s < e) {
			if(arr[s] + arr[e] <= K) {
				cnt++;
				s++;
				e--;
			} else {
				e--;
			}
		}
		
		System.out.println(cnt);	
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