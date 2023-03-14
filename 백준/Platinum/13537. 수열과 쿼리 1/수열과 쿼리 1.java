import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		long[] arr = new long[N+1];
		for (int z = 1; z <= N; z++) {
			arr[z] = readInt();
		}
		
		int M = readInt();
		int result = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		MergeSortTree mtree = new MergeSortTree(N);
		mtree.initarr(arr, 1, 1, N);
		
		for (int z = 0; z < M; z++) {
			i = readInt();
			j = readInt();
			k = readInt();

			result = mtree.query(1, 1, N, i, j , k+1);
			sb.append(result).append('\n');
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
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return flag ? -val : val;
    }
}


class MergeSortTree{
	public long[][] tree;
	
	//생성자 트리생성
	MergeSortTree(int n){
		//n 배열 길이에 따라 트리높이
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		
		tree = new long[Math.toIntExact(treesize)][]; //int변환
	
	}
	
	//머지소트 초기화  배열 , 1 ,  1 , N 
	long[] initarr(long[] arr , int node , int start , int end){
		if (start == end) {
			tree[node] = new long[1];
			tree[node][0] = arr[start];
			return tree[node];
		}
		else {
			long[] ar1 = initarr(arr,node << 1 , start , (start + end) >> 1);
			long[] ar2 = initarr(arr,(node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			tree[node] = new long[ar1.length + ar2.length];
			
			int leftindex = 0;
			int rightindex = 0;
			int cnt = 0;
			while(leftindex < ar1.length && rightindex < ar2.length) {
				if(ar1[leftindex] < ar2[rightindex]) {
					tree[node][cnt++] = ar1[leftindex++];
				} else {
					tree[node][cnt++] = ar2[rightindex++];
				}
			}
			
			while(leftindex < ar1.length) {
				tree[node][cnt++] = ar1[leftindex++];
			}
			
			while(rightindex < ar2.length) {
				tree[node][cnt++] = ar2[rightindex++];
			}	
			return tree[node]; 
		}
	}
	
	// 1 , 1 , N , left ~ right 구간처리
	int query(int node , int start , int end , int left , int right , int k) {
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			int a = Arrays.binarySearch(tree[node], k);
			if(a < 0) {
				a = -a;
				a -= 1;
			}
//			이분탐색 돌려서 위치찾고 size에서 빼주면됨
			return tree[node].length - a;
		} else {
			return query(node << 1 , start , (start + end) >> 1 , left , right , k) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right , k);
		}
		
	}
}