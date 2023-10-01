import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		int S = readInt();
		int D = readInt();
		Pos[] arr = new Pos[S];
		int[] res = new int[S];
		int sum = 0;
		for (int i = 0; i < S; i++) {
			arr[i] = new Pos(readInt() , i) ;
			sum += arr[i].cnt;
		}
		Arrays.sort(arr);
		for (int i = 0; i < S; i++) {
			int t = Math.min(N, D - arr[i].cnt);
			res[arr[i].idx] = t;
			N -= t;
			sum -= arr[i].cnt;
			if(N == 0)
				break;
		}
		StringBuilder sb = new StringBuilder();
		if(sum >= M) {
			for (int i = 0; i < S; i++) {
				sb.append(res[i]).append(' ');
			}	
		} else {
			sb.append("impossible");
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

class Pos implements Comparable<Pos>{
	int cnt;
	int idx;
	public Pos(int cnt, int idx) {
		this.cnt = cnt;
		this.idx = idx;
	}
	@Override
	public int compareTo(Pos o) {
		return this.cnt - o.cnt;
	}
}