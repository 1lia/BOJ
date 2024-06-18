public class Main {
	public static void main(String[] args) throws Exception {
		long T = readLong();
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			long a = readLong();
			long b = readLong();
			long c = readLong();
			if(a == 0 && c == 0 && (b & 1) != 0) {
				sb.append("No");
			} else if(a < c) {
				sb.append("No");
			} else {
				a -= c;
				if((a & 1) != 0) {
					sb.append("No");
				} else {
					sb.append("Yes");
				}	
			}
			sb.append('\n');
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