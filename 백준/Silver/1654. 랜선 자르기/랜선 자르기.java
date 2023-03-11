public class Main {
    public static void main(String[] args) throws Exception{
    	int K = readInt();
    	int N = readInt();
    	int[] arr = new int[K];
    	long start = 0;
    	long end = 0;
    	long mid = 0;
    	int cnt = 0;
    	
    	for (int i = 0; i < K; i++) {
			arr[i] = readInt();
			if(arr[i] > end) {
				end = arr[i];
			}
		}
    	end++;
    	while(start < end) {
    		cnt = 0;
    		mid = (start + end) >> 1;
    	
    		for (int i = 0; i < K; i++) {
				cnt += arr[i] / mid;
			}
    		
    		if(cnt < N) {
    			end = mid;
    		} else {
    			start = mid + 1;
    		}
    	}
    	System.out.println(start-1);
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