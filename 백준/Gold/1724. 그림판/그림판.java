class Main {
    public static int N , M , cnt , min , max;
    public static boolean[][][] map;
    public static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        N = readInt();
        M = readInt();
        int T = readInt();
        map = new boolean[N][M][4]; //위아래왼오
        visit = new boolean[N][M];
        min = 1000000000;

        for (int i = 0; i < T; i++) {
            int x = readInt();
            int y = readInt();
            int x2 = readInt();
            int y2 = readInt();
            if(y > y2){
                int t = y; y = y2; y2 = t;
            }
            if(x > x2){
                int t = x; x = x2; x2 = t;
            }

            if(x == x2 && x != 0 && x != N){
                for (int j = y; j < y2; j++) {
                    map[x - 1][j][1] = true;
                    map[x][j][0] = true;
                }
            }
            if(y == y2 && y != 0 && y != M){
                for (int j = x; j < x2; j++) {
                    map[j][y - 1][3] = true;
                    map[j][y][2] = true;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt = 0;
                if(!visit[i][j]){
                    dfs(i,j);
                    max = Math.max(max , cnt);
                    min = Math.min(min , cnt);
                }
            }
        }
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int x , int y){
        cnt++;
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int dx = x + "0211".charAt(i) - '1';
            int dy = y + "1102".charAt(i) - '1';
            if(isRange(dx , dy) && !map[x][y][i] && !visit[dx][dy]){
                dfs(dx , dy);
            }
        }
    }

    public static boolean isRange(int x , int y){
        if(0 <= x && x < N && 0 <= y && y < M){
            return true;
        }
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