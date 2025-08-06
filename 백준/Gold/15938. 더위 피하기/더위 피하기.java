class Main {
    public static int size;
    public static void main(String[] args) throws Exception {
        int x = readInt();
        int y = readInt();
        int T = readInt();
        x = T - x;
        y = T - y;
        int hx = readInt() + x;
        int hy = readInt() + y;
        int N = readInt();
        size = T << 1;
        boolean[][] map = new boolean[size + 1][size + 1];
        int[][][] dp = new int[size + 1][size + 1][T + 1];
        dp[T][T][0] = 1;
        for (int i = 0; i < N; i++) {
            int a = readInt() + x;
            int b = readInt() + y;
            if(isRange(a , b)) {
                map[a][b] = true;
            }
        }
        if(!isRange(hx , hy)) {
            System.out.println(0);
            return;
        }
        for (int z = 0; z < T; z++) {
            int s = T - z;
            int e = T + z;
            for (int i = s; i <= e; i++) {
                int t = Math.abs(T - i);
                int js = T - z + t;
                int je = T + z - t;
                for (int j = js; j <= je; j++) {
                    if(i == hx && j == hy)
                        continue;
                    for (int k = 0; k < 4; k++) {
                        int dx = i + "1102".charAt(k) - '1';
                        int dy = j + "0211".charAt(k) - '1';
                        if(isRange(dx,dy) && !map[dx][dy]){
                            dp[dx][dy][z + 1] += dp[i][j][z];
                            dp[dx][dy][z + 1] %= 1000000007;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= T; i++) {
            res += dp[hx][hy][i];
            res %= 1000000007;
        }
        System.out.println(res);
    }
    public static boolean isRange(int x , int y){
        if(0 <= x && x <= size && 0 <= y && y <= size)
            return true;
        return false;
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