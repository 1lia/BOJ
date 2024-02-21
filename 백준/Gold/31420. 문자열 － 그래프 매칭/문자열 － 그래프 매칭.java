import java.io.*;

public class Main {
	public static String str;
	public static void main(String[] args) throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		str = br.readLine();
		int M = Integer.parseInt(br.readLine());

		boolean[][] g = new boolean[26][26];
		int[][] now = new int[26][26];
		long[] min = new long[N];
		long[] max = new long[N];
		for (int i = 0; i < M; i++) {
			String m = br.readLine();
			g[m.charAt(0) - 'a'][m.charAt(1) - 'a'] = true;
		}
		int cnt = 0;
		int idx = 0;
		int s = 0;
		for (int i = 1; i < N; i++) {
			int v = findIdx(i - 1);
			int u = findIdx(i);
			if(g[v][u]) {
				if(++now[v][u] == 1) {
					cnt++;
				}
				while(cnt == M) {
					min[s] = i;
					s++;
					v = findIdx(s - 1);
					u = findIdx(s);
					if(--now[v][u] == 0) {
						cnt--;
					}
				}
			} else {
				for (int j = idx; j < i; j++) {
					max[j] = i - 1;
				}
				while(s != i) {
					s++;
					v = findIdx(s - 1);
					u = findIdx(s);
					--now[v][u];
				}
				cnt = 0;
				idx = i;
			}
		}
		for (int i = idx; i < N; i++) {
			max[i] = N - 1;
		}
		long res = 0;
		for (int i = 0; i < N; i++) {
			if(min[i] != 0) {
				res += (max[i] - min[i] + 1);
			}
		}
		System.out.println(res);
	}
	
	public static int findIdx(int idx) {
		return str.charAt(idx) - 'a';
	}
}