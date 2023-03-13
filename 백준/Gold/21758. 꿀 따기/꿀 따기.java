public class Main {
    public static void main(String[] args) throws Exception{
    	StringBuilder sb = new StringBuilder();
    	int N = readInt();
    	int[] map = new int[N];
    	int sum = 0;
    	int midmax = 0;
    	for (int i = 0; i < N; i++) {
			map[i] = readInt();
			sum += map[i];
			if(i != 0 && i != N-1 && map[i] > midmax) {
				midmax = map[i];
			}
		}
    	
//    	벌이 왼쪽 통이 오른쪽
    	int result = 0;
    	int temp = map[0];
    	for (int i = 1; i < N-1; i++) {
			if(result < sum + sum - map[0] - temp - map[i] - map[i])
				result = sum + sum - map[0] - temp - map[i] - map[i];
			temp += map[i];
		}
    	
    	temp = map[N-1];
    	for (int i = N-2; i > 0; i--) {
			if(result < sum + sum - map[N-1] - temp - map[i] - map[i])
				result = sum + sum - map[N-1] - temp - map[i] - map[i];
			temp += map[i];
		}
    	
    	if(result < sum - map[0] - map[N-1] + midmax) {
    		result = sum - map[0] - map[N-1] + midmax;
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