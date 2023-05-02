public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = (int) readLong();

		for (int i = 0; i < T; i++) {
			long N = readLong();
			while((N & 1) == 1 && N != 1) {
				N = (N >> 2) + 1;
			}
			
			if(N == 1)
				sb.append("koosaga").append('\n');
			else
				sb.append("cubelover").append('\n');
		}
		System.out.println(sb);
	}
	public static long readLong() throws Exception {
		long val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}