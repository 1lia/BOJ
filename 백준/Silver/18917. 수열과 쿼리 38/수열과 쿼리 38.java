class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		long M = readInt();
		long sum = 0;
		long xor = 0;
		long temp = 0;
		for (int i = 0; i < M; i++) {
			int a = (int) readInt();
			switch (a) {
			case 1:
				temp = readInt();
				sum += temp;
				xor ^= temp;
				break;
			case 2:
				temp = readInt();
				sum -= temp;
				xor ^= temp;
				break;
			case 3:
				sb.append(sum).append('\n');
				break;
			case 4:
				sb.append(xor).append('\n');
				break;
				}
		}
		System.out.println(sb.toString());
		

	}

	public static long readInt() throws Exception {
		long val = 0;
		boolean flag = false;
		do {
			long c = System.in.read();
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