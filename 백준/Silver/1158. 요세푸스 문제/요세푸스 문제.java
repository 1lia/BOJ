import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int check = N;
		sb = new StringBuilder();
		SegTree seg = new SegTree(N);
		seg.initsum(1, 1, N);
		int count = K;
		sb.append("<");
		while(check > 0) {
			seg.sum(1, 1, N, count);
			check--;
			count += (K-1);
			while(count > check) {
				count -= check;
				if(check == 0)
					break;
			}
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		System.out.println(sb.toString());
		
	}
	static class SegTree{
		public int[] tree;
		
		//생성자 트리생성
		SegTree(int n){
			//n 배열 길이에 따라 트리높이
			double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			long treesize = Math.round(Math.pow(2, treeh));
			tree = new int[Math.toIntExact(treesize)]; //int변환
		
		}
		
		//구간합 초기화  배열 , 1 ,  1 , N 왼쪽개수 오른쪽개수
		int initsum(int node , int start , int end){
			if (start == end)
				return tree[node]+=1;
			else {
				return tree[node] = initsum(node*2 , start , (start + end) / 2) + initsum(node * 2 + 1 , (start + end) / 2 + 1 , end);
			}
		}
		
		// 1 , 1 , N , 가고싶은 index가 왼쪽보다 크면 오른쪽으로
		void sum(int node , int start , int end , int index) {
			tree[node] -= 1;
			if(start == end) {
				sb.append(start).append(",").append(" ");
//				System.out.print(start + " ");		
				return;
			} else {
				if(tree[node * 2] < index) {
					sum(node * 2 + 1 , (start + end) / 2 + 1 , end , index - tree[node * 2]);	
				} else {
					sum(node * 2 , start , (start + end) / 2 , index);
				}
			}
			
		}
	}
}