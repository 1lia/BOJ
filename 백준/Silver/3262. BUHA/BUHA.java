import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        int K = readInt();
        int[][] map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i] , 1);
        }


        for (int i = 0; i < K; i++) {
            int x = readInt();
            int y = readInt();
            int r = (readInt() - 1) >> 1;
            int q = readInt();

            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    if(Math.abs(j - x) <= r && Math.abs(k - y) <= r){
                        map[j][k] &= q;
                    } else{
                        map[j][k] &= ~q;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                res+=map[i][j];
            }
        }
        System.out.println(res);
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