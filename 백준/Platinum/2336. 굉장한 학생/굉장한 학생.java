import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[] score = new int[N + 1];
		int[][] arr = new int[3][N + 1];
		
		for (int i = 1; i <= N; i++) {
			int idx = readInt();
			score[idx] = i;
			arr[0][i] = idx;
		}
		
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][score[readInt()]] = j;
			}
		}
		
		int res = 0;
		Seg seg = new Seg(N);
		
		for (int i = 1; i <= N; i++) {
			seg.update(1, 1, N, arr[1][i], arr[2][i]);
			if(seg.query(1, 1, N, 1, arr[1][i] - 1) > arr[2][i]) {
				res++;
			}
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

class Seg {
	private int[] tree;

	Seg(int n) {
		tree = new int[Math.toIntExact(Math.round(Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1)))];
		Arrays.fill(tree, Integer.MAX_VALUE);
	}

	void update(int node, int start, int end, int index, int value) {
		if (index < start || end < index)
			return;

		if (index == start && end == index) {
			tree[node] = value;
			return;
		}
		
		int mid = (start + end) >> 1;
		int next = node << 1;
		update(next, start, mid, index, value);
		update(next + 1, mid + 1, end, index, value);
		tree[node] = Math.min(tree[next] , tree[next + 1]);
	}

	int query(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return Integer.MAX_VALUE;
		else if (left <= start && end <= right) {
			return tree[node];
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			return Math.min(query(next, start, mid, left, right) , query(next + 1, mid + 1, end, left, right));
		}
	}
}