import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		Suffix_Array suffixArray = new Suffix_Array();
		Suffix[] arr = suffixArray.makeSuffixArray(str);
		int[] lcp = suffixArray.Lcp(str, arr);
		
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i].index+1).append(' ');
		}
		sb.append("\nx ");
		for (int i = 0; i < lcp.length; i++) {
			sb.append(lcp[i]).append(' ');
		}
		
		System.out.println(sb.toString());
	}
}

class Suffix_Array{
	public static String str;
	public static ArrayList<Integer> suffix;
	
	public Suffix[] makeSuffixArray(String s) {
//		문자열 비교
		int N = s.length();

//		index값만 저장한 배열
		Suffix[] suffixes = new Suffix[N];
		
//		소문자만 들어온다고 가정하고 모든 자릿수의 suffixes
		for (int i = 0; i < N; i++) {
			suffixes[i] = new Suffix(i, s.charAt(i) - 'a');
		}
		
//		nextrank는 다음 자리의 rank와 같음
		for (int i = 0; i + 1 < N; i++) {
			suffixes[i].nextRank = suffixes[i+1].rank;
		}
		suffixes[N-1].nextRank = -1;
		
//		rank기준 > nextrank 기준으로 정렬
		Arrays.sort(suffixes);

//		logN 반복문
		int[] temp = new int[N];
		for (int t = 2; t < N; t <<= 1) {
//			초기세팅
			int rank = 0;
			int piv = suffixes[0].rank;
			suffixes[0].rank = rank;
			temp[suffixes[0].index] = 0;
			
			
//			두번째부터 갱신함
			for (int i = 1; i < N; i++) {
//				접미사가 같으면 동일 rank 다르면 rank 하나올림
				if(suffixes[i].rank == piv && suffixes[i].nextRank == suffixes[i-1].nextRank) {
					piv = suffixes[i].rank;
					suffixes[i].rank = rank;
				} else {
					piv = suffixes[i].rank;
					suffixes[i].rank = ++rank;
				}
				temp[suffixes[i].index] = i;
			}
			
			
			for (int i = 0; i < N; i++) {
				int nextindex = suffixes[i].index + t;
				if(nextindex >= N) {
					suffixes[i].nextRank = -1;
					continue;
				}
				suffixes[i].nextRank = suffixes[temp[nextindex]].rank;
			}
//			갱신된 rank로 sort
			Arrays.sort(suffixes);
			if(rank == N - 1) {
				break;
			}
		}
		return suffixes;
	}
	
	public static int[] Lcp(String s , Suffix[] suffixArray) {
		int N = suffixArray.length;
		int[] lcp = new int[N-1];
		int[] suffix = new int[N];
		
//		순서
		for (int i = 0; i < N; i++) {
			suffix[suffixArray[i].index] = i;
		}
		
		int t = 0;
		for (int i = 0; i < N; i++) {
//			마지막이면 없다
			if(suffix[i] == N-1) {
				t = 0;
				continue;
			}
			
			int j = suffixArray[suffix[i] + 1].index;

//			공통 접두사 구하기 같을수록 진행
			while(i + t < N && j + t < N) {
				if(s.charAt(i+t) != s.charAt(j+t))
					break;
				t++;
			}

            lcp[suffix[i]] = t;  
            if(t > 0)
            	t--;
		}
		return lcp;
	}
}
class Suffix implements Comparable<Suffix>{
	int index;
	int rank;
	int nextRank;

	public Suffix(int index, int rank) {
		this.index = index;
		this.rank = rank;
	}

	@Override
	public int compareTo(Suffix o) {
		if(this.rank != o.rank) {
			return this.rank - o.rank;
		}
		return this.nextRank - o.nextRank;
	}
	
}