public class Main {
	public static void main(String[] args) throws Exception {
		long N = readLong();
		long M = readLong();
		long t = gcd(N , M);
		System.out.println(N / t * M);
	}
	public static long gcd(long a, long b) { 
		long t = 0;
		if (a < b) { 
			t = a; 
			a = b; 
			b = t; 
		} 

		while (b != 0) { 
			t = a % b; 
			a = b; 
			b = t; 
		} 
		return a;
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