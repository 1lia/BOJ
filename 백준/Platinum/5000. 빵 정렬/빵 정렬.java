import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
    	int N = readInt();
    	SegTree seg = new SegTree(N+1);
    	int[] arr = new int[N+1];
    	int sum = 0;
    	
    	for (int i = 1; i <= N; i++) {
    		arr[readInt()] = i;
		}
    	for (int i = N; i > 0; i--) {
			seg.update(1, 1, N, arr[i]);
			sum += seg.query(1, 1, N, 1, arr[i]-1);
		}
    	Arrays.fill(seg.tree, 0);
    	for (int i = 1; i <= N; i++) {
    		arr[readInt()] = i;
		}
    	for (int i = N; i > 0; i--) {
    		seg.update(1, 1, N, arr[i]);
			sum += seg.query(1, 1, N, 1, arr[i]-1);
		}

    	if((sum & 1) == 0) {
    		System.out.println("Possible");
    	} else {
    		System.out.println("Impossible");
    	}
    }
    public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;
 
			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}

class SegTree {
	public int[] tree;
	SegTree(int n) {
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new int[Math.toIntExact(treesize)];
	}

	int query(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;
		else if (left <= start && end <= right) {
			return tree[node];
		} else {
			return query(node << 1, start, (start + end) >> 1, left, right)
					+ query((node << 1) + 1, ((start + end) >> 1) + 1, end, left, right);
		}
	}

	void update(int node, int start, int end, int index) {
		if (index < start || end < index) {
			return;
		} else if (start == index && end == index) {
			tree[node]++;
		} else {
			update(node << 1, start, (start + end) >> 1, index);
			update((node << 1) + 1, ((start + end) >> 1) + 1, end, index);
			tree[node] = tree[node << 1] + tree[(node << 1) + 1];
		}
	}
}