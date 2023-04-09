class Main {
	public static void main(String[] args) throws Exception{
		int N = readInt();
		int[][] maxdp = new int[2][3];
		int[][] mindp = new int[2][3];
		int flag1 = 1;
		int flag2 = 0;
		
		for (int i = 0; i < N; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			
			maxdp[flag1][0] = Math.max(maxdp[flag2][0] + a, maxdp[flag2][1] + a);
			maxdp[flag1][1] = Math.max(maxdp[flag2][0] + b, Math.max(maxdp[flag2][1] + b , maxdp[flag2][2] + b));
			maxdp[flag1][2] = Math.max(maxdp[flag2][1] + c, maxdp[flag2][2] + c);
			
			mindp[flag1][0] = Math.min(mindp[flag2][0] + a, mindp[flag2][1] + a);
			mindp[flag1][1] = Math.min(mindp[flag2][0] + b, Math.min(mindp[flag2][1] + b , mindp[flag2][2] + b));
			mindp[flag1][2] = Math.min(mindp[flag2][1] + c, mindp[flag2][2] + c);
			
			flag1 = flag1 ^ flag2;
			flag2 = flag1 ^ flag2;
			flag1 = flag1 ^ flag2;
		}
		
		System.out.print(Math.max(maxdp[flag2][0], Math.max(maxdp[flag2][1] , maxdp[flag2][2])));
		System.out.print(' ');
		System.out.print(Math.min(mindp[flag2][0], Math.min(mindp[flag2][1] , mindp[flag2][2])));

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