public class Main {
	public static void main(String[] args) throws Exception {
		long N = readLong();
		long sum = 0;
		long t = 1;
		while(true) {
			sum += t++;
			if(sum > N) {
				System.out.println(t-2);
				System.exit(0);
			}
		}
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