import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
        
        Aho_Corasick aho = new Aho_Corasick(arr);
        N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
        	if(aho.find(br.readLine())) {
        		sb.append("YES").append('\n');
        	} else
        		sb.append("NO").append('\n');
		}
        System.out.println(sb.toString());
	}
}

class Aho_Corasick{
	public int MAXN = 111111; //size
	public int MAXC = 26;   // 알파벳 개수
	public int[][] trie = new int[MAXN][MAXC]; //트라이 함수
	public int[] fail = new int[MAXN];     //실패함수
	public int[] term = new int[MAXN];    //out 마지막 문자인지 기록하는 배열
	public int piv; //자식 Node의 번호기록
	
	Aho_Corasick(String[] s){
//		사전 트라이로 만들기 output
		for (int i = 0; i < s.length; i++) {
			int p = 0;
			for (int j = 0; j < s[i].length(); j++) {
				if(trie[p][s[i].charAt(j) - 'a'] == 0)
					trie[p][s[i].charAt(j) - 'a'] = ++piv;
				p = trie[p][s[i].charAt(j) - 'a'];
			}
			term[p] = 1;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
//		첫글자 있는걸 큐에다
		for(int i = 0; i < MAXC; i++){
			if(trie[0][i] != 0) 
				q.add(trie[0][i]);
		}
		
//		fail 만들기
		while(!q.isEmpty()){
			int x = q.poll();
			for(int i=0; i<MAXC; i++){
				if(trie[x][i] != 0){
					int p = fail[x];
					while(p != 0 && trie[p][i] == 0) 
						p = fail[p];
					p = trie[p][i];
					fail[trie[x][i]] = p;
					if(term[p] != 0) 
						term[trie[x][i]] = 1;
					q.add(trie[x][i]);
				}
			}
		}
	}
	
	public boolean find(String s){
		int p = 0;

		for(int i = 0 ; i < s.length() ; i++){
			while(p != 0 && trie[p][s.charAt(i) - 'a'] == 0) 
				p = fail[p];
			p = trie[p][s.charAt(i) - 'a'];
			if(term[p] != 0) 
				return true;
		}
		return false;
	}
}