public class Main {
	public static void main(String[] args) throws Exception {
		int N = (int) readLong();
		long[] arr = new long[N];
	
		for (int i = 0; i < N; i++)
			arr[i] = readLong();
		long a = 0 , b = 0;
		for (int i = 0; i < N; i+=2) 
			a += arr[i];
		for (int i = 1; i < N; i+=2) 
			b += arr[i];
		
		if(N == 3 && a > b) 
			System.out.println(-1);
		else 
			System.out.println(Math.abs(b-a));
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