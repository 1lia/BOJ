public class Main{
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int M = readInt() + readInt();
		
		long[] arr = new long[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readlong();
		}
		int a = 0;
		int b = 0;
		int c = 0;
		long d = 0;
		SegTree seg = new SegTree(N);
		seg.init(arr, 1, 1, N);
		for (int i = 0; i < M; i++) {
			a = readInt();
			if(a == 1) {
				b = readInt();
				c = readInt();
				d = readlong();
				seg.update_range(1, 1, N, b, c , d);
			} else {
				b = readInt();
				c = readInt();
				sb.append(seg.query(1, 1, N , b , c)).append('\n');
				
			}
		}
		System.out.println(sb.toString());
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
	
	public static long readlong() throws Exception {
		long val = 0;
		boolean flag = false;
		do {
			long c = System.in.read();
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

class SegTree{
	private long[] tree;
	private long[] lazy;
	
	//생성자 트리생성
	SegTree(int n){
		//n 배열 길이에 따라 트리높이
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new long[Math.toIntExact(treesize)]; //int변환
		lazy = new long[Math.toIntExact(treesize)];
	
	}
	
	//구간합 초기화  배열 , 1 ,  1 , N
	void init(long[] arr , int node , int start , int end){
		if (start == end) {
			tree[node] = arr[start];
		}
		else {
			init(arr, node << 1 , start , (start + end) >> 1); 
			init(arr,(node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			tree[node] = tree[node << 1] +  tree[(node << 1) + 1]; 
		}
	}

//  구간갱신
	void update_range(int node , int start , int end , int left , int right , long value) {
		update_lazy(node , start , end);
		//바꾸는 위치값이 start~end 에 없으면 return
		if (left > end || right < start) {
			return;
		} else if(left <= start && right >= end ) {
//			완벽하게 포함하는 구간이면 노드를 갱신하고 자식에게 lazy만 전파하고 끝냄
			tree[node] += (end - start + 1) * value; //구간개수 * 더해지는값
//			마지막이아니라면 전파
			if(start != end) {
				lazy[node << 1] += value;
				lazy[(node << 1) + 1] += value;
			}
			return;
		}
		update_range(node * 2 , start , (start + end) >> 1 , left , right , value);
		update_range(node * 2 + 1, ((start + end) >> 1) + 1 , end , left , right , value);
		tree[node] =  tree[node * 2] + tree[node * 2 + 1];
	}
	
//	lazy 전파
	void update_lazy(int node , int start , int end){
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if(start != end) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	// 1 , 1 , N , left ~ right 구간의 합 구하기
	long query(int node , int start , int end , int left , int right) {
		update_lazy(node , start , end);
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			return query(node << 1 , start , (start + end) >> 1 , left , right) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
		}
	}
}