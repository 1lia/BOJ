public class Main {
	public static void main(String[] args) throws Exception {
		long[] arr = {0 , 1 , 2 , 5 , 14 , 42 , 132 , 429 , 1430 , 4862 , 16796 , 58786 , 208012 , 742900 , 2674440 , 9694845 , 35357670 , 129644790 , 477638700 , 1767263190 , 6564120420L , 24466267020L , 91482563640L , 343059613650L , 1289904147324L , 4861946401452L , 18367353072152L , 69533550916004L , 263747951750360L , 1002242216651368L , 3814986502092304L};
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = readInt();
			if(N == 0) {
				System.out.println(sb);
				return;
			}
			sb.append(arr[N]).append('\n');
		}
	}

	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}
}