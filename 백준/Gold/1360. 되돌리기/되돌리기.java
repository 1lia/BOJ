import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		boolean[] type = new boolean[N];
		int[] time = new int[N];
		int[] query = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("type")) {
				query[i] = st.nextToken().charAt(0);
				type[i] = true;
			} else {
				query[i] = Integer.parseInt(st.nextToken());
			}
			time[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		int now = Integer.MAX_VALUE;
		for (int i = N - 1; i >= 0; i--) {
			if(time[i] >= now) {
				continue;
			}
			if(!type[i]) {
				now = time[i] - query[i];
			} else {
				sb.append((char) query[i]);
			}
		}
		StringBuilder res = new StringBuilder();
		for (int i = sb.length() - 1; i >= 0; i--) {
			res.append(sb.charAt(i));
		}
		System.out.println(res);
	}
}