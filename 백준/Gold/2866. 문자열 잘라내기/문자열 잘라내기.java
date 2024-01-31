import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		HashSet<String> set = new HashSet<>();
		StringBuilder[] arr = new StringBuilder[C];
		for (int i = 0; i < C; i++) {
			arr[i] = new StringBuilder();
		}
		int res = -1;
		for (int i = R - 1; i >= 0; i--) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arr[j].append(map[i][j]);
				if(set.contains(arr[j].toString())) {
					res = i;
				} else {
					set.add(arr[j].toString());
				}
			}
			set.clear();
		}
		System.out.println(R - res - 2);
	}
}