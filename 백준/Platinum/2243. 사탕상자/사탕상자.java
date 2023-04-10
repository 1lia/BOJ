public class Main {
	public static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		int N = readInt();
		int A = 0;
		int B = 0;
		long C = 0;
		
		SegTree seg = new SegTree(1000001);
		sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			A = readInt();	
			if(A == 1) {
				B = readInt();
				seg.query(1, 1, 1000000, B);
			} else {
				B = readInt();
				C = readLong();	
				seg.update(1, 1, 1000000, B, C);
			}
		}	
		System.out.println(sb.toString());
	}
	public static long readLong() throws Exception {
        long val = 0;
        boolean flag = false;
        do {
            long c = System.in.read();
            if (c == '-') {
                flag = true;
                continue;
            }
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return flag ? -val : val;
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
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return flag ? -val : val;
    }
	static class SegTree{
		private long[] tree;
		
		SegTree(int n){
			double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			long treesize = Math.round(Math.pow(2, treeh));
			tree = new long[Math.toIntExact(treesize)];   // sum
		}
		
		// 1 , 1 , N , left ~ right
		void query(int node , int start , int end , long count) {
			if(start == end) {
				tree[node]--;
				sb.append(start).append('\n');
				return;
			}
			if(tree[(node << 1)] >= count) {
				query(node << 1 , start , (start + end) >> 1 , count);
			} else {
				query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , count - tree[node << 1]);
			}
			tree[node] = tree[node << 1] + tree[(node << 1) + 1];
		}
		
		//갱신 추가
		void update(int node , int start , int end , int index , long value) {
			if (index < start || end < index) {
				return;
			} else if(start == index && end == index) {
				tree[node] += value;
			} else {
				update(node << 1 , start , (start + end) >> 1 , index , value);
				update((node << 1) + 1, ((start + end) >> 1) + 1 , end , index , value);
				tree[node] = tree[node << 1] + tree[(node << 1) + 1];
			}
		}
	}
}