import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Long[] arr = new Long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		ArrayList<Long> dp = new ArrayList<>();
		int s = 1;
		while(s < N && str.charAt(s) == str.charAt(0)) {
			s++;
		}
		if(N > 2 && s < N) {
			long t = arr[s];
			for (int i = s + 1; i < N; i++) {
				if(str.charAt(i) != str.charAt(i-1)) {
					dp.add(t);
					t = arr[i];
				} else {
					t = Math.max(t, arr[i]);
				}
			}
			Collections.sort(dp , Collections.reverseOrder());
			long res = 0;
			int a = (dp.size() + 1) >> 1;
			for (int i = 0; i < a; i++) {
				res += dp.get(i);
			}
			System.out.println(res);
		} else {
			System.out.println(0);
		}
	}
}