import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int K = readInt();
		int D = readInt();
		int[] cnt = new int[K+1];
		Pos[] arr = new Pos[N];
		for (int i = 0; i < N; i++) {
			int M = readInt();
			arr[i] = new Pos(readInt());
			for (int j = 0; j < M; j++) {
				arr[i].arr.add(readInt());
			}
		}
		Arrays.sort(arr);
		int s = 0;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < arr[i].arr.size(); j++) {
				cnt[arr[i].arr.get(j)]++;
			}
			while(arr[i].d - arr[s].d > D) {
				for (int j = 0; j < arr[s].arr.size(); j++) {
					cnt[arr[s].arr.get(j)]--;
				}
				s++;
			}
			int count = i - s + 1;
			int a = 0;
			int b = 0;
			
			for (int j = 1; j <= K; j++) {
				if(cnt[j] != 0) {
					a++;
				}
				if(cnt[j] == count) {
					b++;
				}
			}
			res = Math.max(res, count * (a - b));
		}
		System.out.println(res);
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
	public Pos(int d) {
		this.d = d;
		arr = new ArrayList<>();
	}
	int d;
	ArrayList<Integer> arr;
	@Override
	public int compareTo(Pos o) {
		return this.d - o.d;
	}
}