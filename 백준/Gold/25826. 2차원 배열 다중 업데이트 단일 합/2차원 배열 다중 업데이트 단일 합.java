public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		long[][] map = new long[N][N];
		long[][] update = new long[N+1][N+1];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = readLong();
			}
		}
		
		for (int i = 1; i < M; i++) {
			readLong();
			int x1 = readInt();
			int y1 = readInt();
			int x2 = readInt();
			int y2 = readInt();
			long v = readLong();
			
			update[x1][y1] += v;
			update[x1][y2+1] -= v; 
			update[x2+1][y1] -= v;
			update[x2+1][y2+1] += v; 
		}
		
		
		for (int i = 0; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				update[i][j] += update[i][j-1];
			}
		}
		
		for (int i = 0; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				update[j][i] += update[j-1][i];
			}
		}
		readLong();
		int x1 = readInt();
		int y1 = readInt();
		int x2 = readInt();
		int y2 = readInt();
		long result = 0;
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				result += (map[i][j] + update[i][j]);
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
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return flag ? -val : val;
    }
	
	public static long readLong() throws Exception {
        long val = 0;
        boolean flag = false;
        do {
            long c = System.in.read();
            if (c == '-') {
                flag = true;
                continue;
            }
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return flag ? -val : val;
    }
}