import java.util.Arrays;

public class Main {
	public static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		sb = new StringBuilder();
		readInt();
		int M = readInt();
		int K = readInt();
		int[] arr = new int[M + 1];

		for (int i = 1; i <= M; i++) {
			arr[i] = readInt();
		}
		Arrays.sort(arr);
		SegTree seg = new SegTree(M);
		seg.initmax(arr, 1, 1, M);		

		for (int i = 0; i < K; i++) {
			int a = readInt();
			seg.update(1, 1, M, a);
		}
		System.out.println(sb.toString());
	}
	static class SegTree{
		private int[] tree;
		
		//생성자 트리생성
		SegTree(int n){
			//n 배열 길이에 따라 트리높이
			double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			long treesize = Math.round(Math.pow(2, treeh));
			tree = new int[Math.toIntExact(treesize)];
		
		}
		
		//구간합 초기화  배열 , 1 ,  1 , N
		int initmax(int[] arr , int node , int start , int end){
			if (start == end)
				return tree[node] = arr[start];
			else {
				return tree[node] = Math.max(initmax(arr,node*2 , start , (start + end) / 2) , initmax(arr,node * 2 + 1 , (start + end) / 2 + 1 , end));
			}
		}
		//갱신 
		int update(int node , int start , int end , int value) {
			//바꾸는 위치값이 start~end 에 없으면 return
			if(start == end) {
				sb.append(tree[node]).append("\n");
				tree[node] = 0;
				return tree[node];
				
			} else if(value < tree[node * 2]){ //왼쪽보다 작으면
				update(node * 2 , start , (start + end) / 2  , value);
				tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
			} else {
				update(node * 2 + 1, (start + end) / 2 + 1 , end , value);
				tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
			}
			return tree[node];
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