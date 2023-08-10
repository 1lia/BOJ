public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		long T = readLong();
		
		while(T-->0) {
			long N = readLong();
			long cnt = 0;
			long t = readLong();
			for (int i = 1; i < N; i++) {
				long p = readLong();
				if(t > 0) {
					if(p > 0) {
						cnt++;
						t = p;
					} else if(t + p > 0) {
						t += p;
					} else {
						cnt++;
						t = p;
					}
				} else if(t < 0){
					if(p <= 0 || p + t > 0) {
						t += p;
					} else {
						cnt--;
						t = p;
					}
				} else {
					t = p;
				}
			}
			
			if(t > 0) {
				cnt++;
			} else {
				cnt--;
			}

			if(cnt > 0) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}	
		}
		System.out.println(sb);
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