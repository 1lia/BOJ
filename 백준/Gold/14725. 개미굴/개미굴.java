import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			String[] arr = new String[M];
			for (int j = 0; j < M; j++) {
				arr[j] = st.nextToken();
			}	
			trie.insert(arr);
		}
		trie.print(trie.rootNode , 0);
		System.out.println(trie.sb.toString());
	}
}

class Trie{
	//루트노드
	Node rootNode = new Node();
	StringBuilder sb = new StringBuilder();
	//문자열 저장하는 함수
	void insert(String[] str) {
		//처음부터 시작
		Node node = this.rootNode;
		
		//String 길이만큼 나아가면서 없으면 만들어주고 있으면 넘어감
		for (int i = 0; i < str.length; i++) {
			node = node.childNode.computeIfAbsent(str[i], Key -> new Node());
		}
	}
	
	void print(Node node , int deep) {
		Object[] arr = node.childNode.keySet().toArray();
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < deep; j++) {
				sb.append("--");
			}
			sb.append(arr[i]).append('\n');
			print(node.childNode.get(arr[i]) , deep + 1);
		}
	}
	
	//node inner class
	private static class Node{
		Map<String , Node> childNode = new HashMap<>(); //자식노드
	}
}