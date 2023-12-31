import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		StringBuilder t = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		int[] A = new int[N + 2];
		int[] B = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			t.setLength(0);
			System.out.println(t.append("? 1 ").append(i));	
			A[i] = Integer.parseInt(br.readLine());

			t.setLength(0);
			System.out.println(t.append("? ").append(i).append(" ").append(N));	
			B[i] = Integer.parseInt(br.readLine());
		}	
		
		for (int i = 1; i <= N; i++) {
			if(A[i] == A[i-1] + 1 && B[i] == B[i + 1] + 1) {
				cnt++;
				sb.append(' ').append(i);
			}
		}
		System.out.println(res.append("! ").append(cnt).append(sb));
	}
}