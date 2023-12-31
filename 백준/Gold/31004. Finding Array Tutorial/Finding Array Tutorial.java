public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int cnt = 0;
		StringBuilder t = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		int[] A = new int[N + 2];
		int[] B = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			t.setLength(0);
			System.out.println(t.append("? 1 ").append(i));	
			A[i] = readInt();

			t.setLength(0);
			System.out.println(t.append("? ").append(i).append(" ").append(N));	
			B[i] = readInt();
		}	
		
		for (int i = 1; i <= N; i++) {
			if(A[i] == A[i-1] + 1 && B[i] == B[i + 1] + 1) {
				cnt++;
				sb.append(' ').append(i);
			}
		}
		System.out.println(res.append("! ").append(cnt).append(sb));
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