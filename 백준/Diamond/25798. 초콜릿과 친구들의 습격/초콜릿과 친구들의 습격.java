public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = readInt();
		for (int i = 0; i < T; i++) {
			int M = readInt();
			int N = readInt();
			int K = readInt();
			int result = N * M >> 1;
			if(K == 0) {
				sb.append(result).append('\n');
			} else if(K == 1){
				readInt();readInt();
				sb.append(result - 1).append('\n');
			} else if(K == 2) {
				int t = readInt() + readInt() + readInt() + readInt();
				if((t & 1) == 1) {
					sb.append(result - 1).append('\n');
				} else {
					sb.append(result - 2).append('\n');
				}
			} else if(K == 3) {
				int[] arr = new int[3];
				int cnt = 0;
				for (int j = 0; j < 3; j++) {
					arr[j] = readInt() + readInt();
				}
				for (int j = 0; j < 3; j++) {
					if((arr[j] & 1) == 1) {
						cnt++;
					}
				}
				if(cnt == 1 || cnt == 2) {
					sb.append(result - 2).append('\n');
				} else {
					sb.append(result - 3).append('\n');
				}
			} else if(K == 4) {
				int[][] arr = new int[4][2];
				int cnt = 0;
				for (int j = 0; j < 4; j++) {
					arr[j][1] = readInt();
					arr[j][0] = readInt();
				}

				for (int j = 0; j < 4; j++) {
					if(((arr[j][0] + arr[j][1]) & 1) == 1) {
						cnt++;
					}
				}
				
				if(cnt == 0 || cnt == 4) {
					sb.append(result - 4).append('\n');
				} else if(cnt == 1 || cnt == 3){
					sb.append(result - 3).append('\n');
				} else{
					if(check(arr , N , M)) {
						sb.append(result - 3).append('\n');
					} else {
						sb.append(result - 2).append('\n');
					}
				}
			}
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	public static boolean check(int[][] arr , int n , int m) {
		int[][] cnt = new int[4][2];
		
		for (int i = 0; i < 4; i++) {
			int x = arr[i][0];
			int y = arr[i][1];
	
//			row비교
			if(y == 1) {
				if(x == 1) {
					cnt[0][1]++;
				} else if(x == 2) {
					cnt[0][0]++;
				} else if(x == n - 1) {
					cnt[1][0]++;
				} else if(x == n) {
					cnt[1][1]++;
				}
			} else if(y == 2) {
				if(x == 1) {
					cnt[0][0]++;
				} else if(x == n) {
					cnt[1][0]++;
				}
			} else if(y == m - 1) {
				if(x == 1) {
					cnt[2][0]++;
				} else if(x == n) {
					cnt[3][0]++;
				}
			} else if(y == m) {
				if(x == 1) {
					cnt[2][1]++;
				} else if(x == 2) {
					cnt[2][0]++;
				} else if(x == n - 1) {
					cnt[3][0]++;
				} else if(x == n) {
					cnt[3][1]++;
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			if(cnt[i][0] == 2 && cnt[i][1] == 0) {
				return true;
			}
		}
		return false;
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