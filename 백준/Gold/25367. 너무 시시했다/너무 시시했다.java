public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		long q = readLong();
		while(q-->0) {
			long x = readLong();
			long y = readLong();
			if(((x - y) & 1) == 1) {
				sb.append(0).append('\n');
			} else {
				long t = (x - y) >> 1;
				long res = 1;
				for (int i = 0; i < 61; i++) {
					long b = (1L << i);
					if ((t & b) != 0) {
						if ((y & b) != 0) {
							res = 0;
							break;
						}
					}
					else if((y & b) != 0){
						res <<= 1;
					}
				}
				sb.append(res).append('\n');
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