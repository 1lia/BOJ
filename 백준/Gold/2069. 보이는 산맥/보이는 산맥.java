import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		Pos[] arr = new Pos[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new Pos(readInt() , readInt());
		}
		
		Arrays.sort(arr);
		int res = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			if(max > arr[i].y) {
				continue;
			} else if(max > arr[i].x) {
				res -= (max - arr[i].x) * (max - arr[i].x);
			}
			res += (arr[i].y - arr[i].x) * (arr[i].y - arr[i].x);	
			max = Math.max(max, arr[i].y);
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
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Pos o) {
		if(this.x == o.x)
			return this.y - o.y;
		return this.x - o.x;
	}	
}