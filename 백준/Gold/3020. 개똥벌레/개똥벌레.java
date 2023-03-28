public class Main {
	public static void main(String[] args) throws Exception{
		int N = readInt();
		int H = readInt();
		
		int[] arr = new int[H+1];
			
		for (int i = 0; i < N; i++) {
			int a = readInt();

			if((i & 1) == 0) {
				arr[1] += 1;
				arr[a+1] -= 1;
			} else {
				arr[H-a+1] += 1;
			}
		}

		int result = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 1; i <= H; i++) {
			arr[i] = arr[i-1] + arr[i];
			if(arr[i] < result) {
				count = 1;
				result = arr[i];
			} else if(arr[i] == result){
				count++;
			}
		}
		
		System.out.println(result + " " + count);
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