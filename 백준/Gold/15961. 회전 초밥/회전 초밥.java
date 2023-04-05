public class Main {
	public static void main(String[] args) throws Exception{
		int N = readInt();
		int d = readInt();
		int k = readInt();
		int c = readInt();
		
		
		int[] arr = new int[N + k - 1];
		int[] count = new int[d+1];
		
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		
		for (int i = 0; i + 1 < k; i++) {
			arr[i+N] = arr[i];
		}

//		k개의 개수를 담는다
		int numcnt = 0;
		for (int i = 0; i < k; i++) {
			count[arr[i]]++;
			if(count[arr[i]] == 1)
				numcnt++;
		}
		
		int result = numcnt;
		
		for (int i = 1; i < N; i++) {
			count[arr[i-1]]--;
			
			if(count[arr[i-1]] == 0) {
				numcnt--;
			}
			count[arr[i+k-1]]++;
			
			if(count[arr[i+k-1]] == 1) {
				numcnt++;
			}
			
			if(count[c] == 0) {
				if(result < numcnt + 1) {
					result = numcnt + 1;
				}
			} else {
				if(result < numcnt) {
					result = numcnt;
				}
			}
		}
		System.out.println(result);
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