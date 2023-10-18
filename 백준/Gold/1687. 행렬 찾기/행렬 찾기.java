import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++) {
				if(s.charAt(j-1) == '0') {
					arr[i][j] = arr[i-1][j] + 1;
				}
			}
		}
		Stack<Integer> s = new Stack<>();
		int res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				while (!s.isEmpty() && arr[i][s.peek()] > arr[i][j]) {
					int a = s.pop();
					if(s.isEmpty()) {
						res = Math.max(res, arr[i][a] * (j - 1));
					} else {
						res = Math.max(res, arr[i][a] * (j - s.peek() - 1));
					}
					
				}
				s.add(j);
			}
			while(!s.isEmpty()) {
				int a = s.pop();
				if(s.isEmpty()) {
					res = Math.max(res, arr[i][a] * M);
				} else {
					res = Math.max(res, arr[i][a] * (M - s.peek()));
				}
			}
		}
		System.out.println(res);
	}
}