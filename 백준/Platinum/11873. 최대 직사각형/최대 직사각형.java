import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		Stack<Integer> s = new Stack<>();
		while(true) {
			int N = readInt();
			int M = readInt();
			if(N == 0) {
				System.out.println(sb);
				return;
			}
			
			int[][] arr = new int[N+1][M+1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if(readInt() == 1) {
						arr[i][j] = arr[i-1][j] + 1;
					}
				}
			}
			s.clear();
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
			sb.append(res).append('\n');
		}	
	}
	
	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}
}