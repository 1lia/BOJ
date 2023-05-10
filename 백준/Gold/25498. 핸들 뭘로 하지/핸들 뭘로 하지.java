import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		Deque<Integer> q = new ArrayDeque<Integer>();
		ArrayList<Integer>[] g = new ArrayList[N];
		boolean[] visit = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			g[i] = new ArrayList<>();
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			g[a].add(b);
			g[b].add(a);
		}
		
		q.offer(0);
		visit[0] = true;
		int depsize = 1;
		while(!q.isEmpty()) {
			int v = q.pollFirst();
			depsize--;
			for (int i = 0; i < g[v].size(); i++) {
				int t = g[v].get(i);
				if(!visit[t]) {
					visit[t] = true;
					while(q.size() > depsize && s.charAt(q.peekLast()) < s.charAt(t)) {
						q.pollLast();
					}

					if(q.size() == depsize) {
						q.offer(t);
					} else if(s.charAt(q.peekLast()) == s.charAt(t)) {
						q.offer(t);
					}
				}
			}
			
			if(depsize == 0) {
				sb.append(s.charAt(v));
				depsize = q.size();
			}
		}
		System.out.println(sb);
	}
}