public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int[] arr = new int[N];
		int[] res = new int[1000001];
		boolean[] visit = new boolean[1000001];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
			visit[arr[i]] = true;
			
		}
		
		for (int i = 0; i < N; i++) {
			int t = arr[i] + arr[i];
			while(t <= 1000000) {
				if(visit[t]) {
					res[arr[i]]++;
					res[t]--;
				}	
				t += arr[i];
			}
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(res[arr[i]]).append(' ');
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
}