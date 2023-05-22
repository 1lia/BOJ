public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = (int) Math.ceil(Math.sqrt(N));
		int sum = 0;
		int result = 4;
		for (int i = 1; i <= M; i++) {
			sum += i*i;
			if(sum == N) {
				result = 1;
				break;
			}
			for (int j = 1; j <= M; j++) {
				sum += j*j;
				if(sum == N && result > 2) {
					result = 2;
				}
				for (int k = 1; k <= M; k++) {
					sum += k*k;
					if(sum == N && result > 3) {
						result = 3;
					}
					sum -= k*k;
				}
				sum -= j*j;
			}
			sum -= i*i;
		}
		System.out.println(result);
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