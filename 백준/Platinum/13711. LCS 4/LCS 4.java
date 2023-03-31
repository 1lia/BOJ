import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		int[] aa = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			aa[Integer.parseInt(st.nextToken())] = i-1;
		}
		
		st = new StringTokenizer(br.readLine());
			
		for (int i = 0; i < N; i++) {
			arr[aa[Integer.parseInt(st.nextToken())]] = i;
		}

		int index = 0;
		dp[0] = arr[0];
		for (int i = 1; i < N; i++) {	
//			맨뒤값보다 크면 맨뒤에넣어줌
			if(dp[index] <= arr[i]) {
				dp[++index] = arr[i];        
			} else {
				int temp = upper_bound(dp , arr[i] , index);
				dp[temp] = arr[i];
			}
		}
		
		System.out.println(index+1);
	}
	
	private static int upper_bound(int[] dp, int y , int index) {
		int s = 0;
		int e = index;
		int m = 0;
		while(s != e) {
			m = ((s + e) >> 1);
			
			if(dp[m] < y) {
				s = m + 1;
			} else {
				e = m;
			}
			
		}
		return s;
	}
}