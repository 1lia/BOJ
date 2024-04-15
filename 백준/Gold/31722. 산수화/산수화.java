import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		int[][] white = new int[N][M];
		int[][] l = new int[N][M];
		int[][] r = new int[N][M];
		int[] whiteCnt = new int[N + 1];
		int[] blackCnt = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i].charAt(j) == '.') {
					if(i == 0 || j == 0) {
						white[i][j] = 1;
					} else {
						white[i][j] = Math.min(white[i - 1][j], Math.min(white[i][j - 1], white[i - 1][j - 1])) + 1;
					}
					whiteCnt[white[i][j]]++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i].charAt(j) == '#') {
					if(i == 0 || j == 0) {
						l[i][j] = 1;
					} else {
						l[i][j] = Math.min(l[i - 1][j], l[i][j - 1]) + 1;
					}	
				}
			}
			
			for (int j = M - 1; j >= 0; j--) {
				if(arr[i].charAt(j) == '#') {
					if(i == 0 || j == M - 1) {
						r[i][j] = 1;
					} else {
						r[i][j] = Math.min(r[i - 1][j], r[i][j + 1]) + 1;
					}	
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				blackCnt[Math.min(l[i][j], r[i][j])]++;
			}
		}
		
		for (int i = N - 1; i > 0; i--) {
			whiteCnt[i] += whiteCnt[i + 1];
			blackCnt[i] += blackCnt[i + 1];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(blackCnt[i]).append(' ');
		}
		sb.append('\n');
		for (int i = 1; i <= N; i++) {
			sb.append(whiteCnt[i]).append(' ');
		}
		System.out.println(sb);
	}
}