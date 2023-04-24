import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int N = readInt();
		int[] arr = new int[N+2];
		arr[N+1] = N+1;
		for (int i = 1; i <= N; i++) {
			arr[i] = readInt();
		}
		int[] dp = new int[4];
		int cnt = 0;
		for (int i = 1; i - 1 <= N; i++) {
			if(arr[i] - arr[i-1] != 1 && arr[i] - arr[i-1] != -1)
				dp[cnt++] = i;
		}
		
		int cal = cnt;
		int[][] dxy = {{-1,-1},{-1,0},{0,-1},{0,0}};
		for (int i = 0; i < cnt; i++) {	
			for (int j = i+1; j < cnt; j++) {
				for (int z = 0; z < 4; z++) {
					int l = dp[i] + dxy[z][0];
					int r = dp[j] + dxy[z][1];
					int count = 0;
					boolean flag1 = false;
					boolean flag2 = false;
					if(l == 0 || r == N+1)
						continue;

					if(Math.abs(arr[l] - arr[r + 1]) == 1){
						count++;
						flag1 = true;
					}
					
					if(Math.abs(arr[l - 1] - arr[r]) == 1){
						count++;
						flag2 = true;
					}
					
					if(cal - count <= 2 && count > 0) {
						count = 0;
						if(r+1 == dp[j] && flag2)
							count++;
						if(l == dp[i] && flag1)
							count++;
						
						if(cal - count <= 2) {
							for (int k = i + 1; k < j; k++) {
								dp[k] = l + r - dp[k] + 1; 
							}
							if(r+1 == dp[j] && flag2)
								dp[j] = 0;
							else if(Math.abs(arr[dp[j]] - arr[l-1]) == 1)
								dp[j] = l + 1;
							else
								dp[j] = l;
									
							if(l == dp[i] && flag1)
								dp[i] = 0;
							else if(Math.abs(arr[r+1] - arr[l]) == 1)
								dp[i] = r;
							else
								dp[i] = r+1;
							
							sb.append(l).append(' ').append(r).append('\n');
							cal -= count;
							break;
						}
					}
				}
				if(cal <= 2)
					break;
			}
			
			if(cal <= 2)
				break;
		}
		Arrays.sort(dp);
		cal = 0;
		for (int i = 0; i < 4; i++) {
			if(dp[i] != 0) {
				cal++;
			}
		}
		if(cal >= 2) {
			for (int i = 0; i < 4; i++) {
				if(dp[i] != 0) {
					if(cal == 2) {
						sb.append(dp[i]).append(' ');
						cal--;
					} else {
						sb.append(dp[i]-1);
					}
				}
			}
		} else {
			sb.append(1).append(' ').append(1);
		}
		
		if(sb.length() < 4) {
			sb.append('\n').append(1).append(' ').append(1);
		}
		System.out.println(sb);
	}
	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}