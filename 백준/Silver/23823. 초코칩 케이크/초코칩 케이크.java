public class Main{
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		int n = readInt();
		int q = readInt();
		
		int[] row = new int[n+1];
		int[] col = new int[n+1];
		int rowmax = 0;
		int colmax = 0;
		int rowcnt = 0;
		int colcnt = 0;

		for (int i = 0; i < q; i++) {
			int t = readInt();
			int a = readInt();
			if(t == 1) {
				row[a]++;
				if(row[a] > rowmax) {
					rowmax = row[a];
					rowcnt = 1;
				} else if(row[a] == rowmax) {
					rowcnt++;
				}
			} else {
				col[a]++;
				if(col[a] > colmax) {
					colmax = col[a];
					colcnt = 1;
				} else if(col[a] == colmax) {
					colcnt++;
				}
			}	

			if(colcnt == 0) {
				sb.append(rowcnt * n).append('\n');
			} else if(rowcnt == 0){
				sb.append(colcnt * n).append('\n');
			} else {
				sb.append(colcnt * rowcnt).append('\n');
			}	
		}
		System.out.println(sb.toString());
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