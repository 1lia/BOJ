import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		Prime p = new Prime();
		p.linear_sieve(5000000);
		for (int z = 0; z < N; z++) {
			p.factorization(readInt());
		}
		System.out.println(p.sb);
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

class Prime{
	private int[] spf;
	private ArrayList<Integer> prime;
	public StringBuilder sb;
	public void linear_sieve(int n) {
		spf = new int[n+1];
		prime = new ArrayList<>();
		sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			if(spf[i] == 0) {
				prime.add(i);
				spf[i] = i;
			}
			
			for (int j = 0; j < prime.size(); j++) {
				int p = prime.get(j);
				if(i * prime.get(j) > n) break;
				spf[i*p] = p;
				if(i % p == 0) break;
			}
		}
	}

	public void factorization(int n) {
		while(n != 1) {
			sb.append(spf[n]).append(' ');
			n /= spf[n];
		}
		sb.append('\n');
	}
}