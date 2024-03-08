import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int Q = readInt();
		int[] arr = new int[N];
		int[] res = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(arr[0]);
		res[0] = 1;
		for (int i = 1; i < N; i++) {
			if(list.get(list.size() - 1) < arr[i]) {
				list.add(arr[i]);
				res[i] = list.size();
			} else if(arr[i] <= list.get(0)) {
				list.set(0, arr[i]);
				res[i] = 1;
			} else {
				int s = 0;
				int e = list.size() - 1;
				
				while(s < e) {
					int m = ((s + e) >> 1);
					if(list.get(m) < arr[i]) {
						s = m + 1;
					} else {
						e = m;
					}
				}
				list.set(s, arr[i]);
				res[i] = s + 1;
			}
		}
		
		list.clear();
		list.add(arr[N - 1]);

		for (int i = N - 2; i >= 0; i--) {
			if(list.get(list.size() - 1) > arr[i]) {
				list.add(arr[i]);
				res[i] += (list.size() - 1);
			} else if(arr[i] >= list.get(0)) {
				list.set(0, arr[i]);
			} else {
				int s = 0;
				int e = list.size() - 1;
				
				while(s < e) {
					int m = ((s + e) >> 1);
					if(list.get(m) > arr[i]) {
						s = m + 1;
					} else {
						e = m;
					}
				}
				list.set(s, arr[i]);
				res[i] += s;
			}
		}
		while(Q-->0) {
			sb.append(res[readInt() - 1]).append('\n');
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
	int s;
	int e;
	public Pos(int s, int e) {
		this.s = s;
		this.e = e;
	}
	@Override
	public int compareTo(Pos o) {
		return this.e - o.e;
	}
}