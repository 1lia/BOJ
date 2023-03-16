import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static Trie color;
	public static HashSet<String> nameset = new HashSet<>(); 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		String s = null;
		color = new Trie();
		for (int i = 0; i < C; i++) {
			s = br.readLine();
			color.insert(s);
		}
		
//		for (int i = 0; i < 200; i++) {
//			System.out.println(Arrays.toString(color.trim[i]));
//		}
		
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			nameset.add(s);
		}
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			String str = br.readLine();
			if(color.search(str)) {
				sb.append("Yes").append('\n');
			} else {
				sb.append("No").append('\n');
			}
		}
		System.out.println(sb.toString());
	}

	static class Trie {
		// 루트노드
		int[][] trie = new int[4001200][26];
		boolean[][] trim = new boolean[4001200][26];
		int piv = 0;

		// 문자열 저장하는 함수
		void insert(String str) {
			int p = 0;
			for (int i = 0; i < str.length(); i++) {
//				0이면 만들어주고 있으면 piv를 그쪽으로
				if(trie[p][str.charAt(i) - 'a'] == 0) {
					trie[p][str.charAt(i) - 'a'] = ++piv;	
				}
				if(i == str.length() - 1)
					trim[p][str.charAt(str.length() - 1) - 'a'] = true;
				p = trie[p][str.charAt(i) - 'a'];	
			}
		}

		boolean search(String str) {
			int p = 0;
			for (int i = 0; i < str.length(); i++) {
				if(trim[p][str.charAt(i) - 'a'] && nameset.contains(str.substring(i+1))) {;
					return true;
				}
				
				if (trie[p][str.charAt(i) - 'a'] == 0) {
					return false;
				} 
				
				p = trie[p][str.charAt(i) - 'a'];
			}
			return false;
		}
	}
}