import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		
		int M = readInt();
		Query[] query = new Query[M];
		int[] result = new int[M];
		
		for (int i = 0; i < M; i++) {
			query[i] = new Query(readInt() , readInt() , i);
		}

//		제곱근
		int sqrt = (int) Math.sqrt(N);
		Comparator<Query> compar = new Comparator<Query>() {
			@Override
			public int compare(Query o1, Query o2) {
				int s = o1.start / sqrt;
				int e = o2.start / sqrt;
				if(s < e) {
					return -1;
				} else if(s == e) {
					if(o1.end < o2.end)
						return -1;
					else if(o1.end == o2.end){
						return 0;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		};
		Arrays.sort(query, compar);
		
//		index에 몇개가있는지개수확인
//		index만큼 가진 숫자가 몇개인지 확인
		int[] numcnt = new int[1000200];
//		현재 최대값
		int cnt = 1;
		int start = 1;
		int end = 1;
		
//		쿼리진행 0번째는 가지고시작
		numcnt[arr[1]]++;
		for (int i = 0; i < M; i++) {
//			먼저 증가시키고 담는다 그래야 end값까지 담아짐
			while(end < query[i].end) {
				numcnt[arr[++end]]++;   //배열에 담겨있는값을 구간index를 하나 증가시키고

//				하나증가하면
				if(arr[end] > 0 && numcnt[arr[end]] == 1) {
					cnt++;
				}
			}
//			end를 내려야될때
			while(end > query[i].end) {
				numcnt[arr[end--]]--;   //감소하고 내림

//				뺀게 0이될때
				if(numcnt[arr[end + 1]] == 0) {
					cnt--;
				}
			}
			
//			start를 올려서 query의 시작점에 맞춰줌
			while(start < query[i].start) {
				numcnt[arr[start++]]--;   //배열에 담겨있는값을 빼고 증가

//				최대개수보다 더많아진게 나오면 그걸로 교체
				if(numcnt[arr[start - 1]] == 0) {
					cnt--;
				}
			}
			
			while(start > query[i].start) {
				numcnt[arr[--start]]++;   //배열에 담겨있는값을 빼고 증가

//				최대개수보다 더많아진게 나오면 그걸로 교체
				if(numcnt[arr[start]] == 1) {
					cnt++;
				}
			}
			result[query[i].index] = cnt;
		}
		for (int i = 0; i < M; i++) {
			sb.append(result[i]).append('\n');
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
}

class Query{
	int start;
	int end;
	int index;
	
	public Query(int start, int end , int index) {
		this.start = start;
		this.end = end;
		this.index = index;
	}
}