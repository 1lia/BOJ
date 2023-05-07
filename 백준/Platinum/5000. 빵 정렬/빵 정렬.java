import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
    	int N = readInt();
    	FenwickTree fenwick = new FenwickTree(N+1);
    	int[] arr = new int[N+1];
    	int sum = 0;
    	
    	for (int i = 1; i <= N; i++) {
    		arr[readInt()] = i;
		}
    	for (int i = N; i > 0; i--) {
			fenwick.update(arr[i] , 1);
			sum += fenwick.query(arr[i]-1);
		}
    	Arrays.fill(fenwick.tree, 0);
    	for (int i = 1; i <= N; i++) {
    		arr[readInt()] = i;
		}
    	for (int i = N; i > 0; i--) {
    		fenwick.update(arr[i] , 1);
			sum += fenwick.query(arr[i]-1);
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

class FenwickTree {
    public int[] tree;
    
    public FenwickTree(int n) {
        tree = new int[n + 1];
    }
    
    public void update(int i, int val) {
        while (i < tree.length) {
            tree[i] += val;
            i += i & -i;
        }
    }
    
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        return sum;
    }
}