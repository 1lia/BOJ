public class Main {
    public static final int MOD = 9_999_991;
    public static int[][] comb;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        comb = new int[21][21];
        buildComb();

        int T = readInt();
        while(T-->0){
            int N = readInt();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = readInt();
            }
            sb.append(slove(arr, 0, N) % MOD).append('\n');
        }
        System.out.println(sb);
    }

    public static int slove(int[] arr , int l , int r){
        int n = r - l;
        if (n <= 1)
            return 1;

        int root = arr[l];
        int[] left = new int[n - 1];
        int[] right = new int[n - 1];

        int lc = 0, rc = 0;
        
        for (int i = l + 1; i < r; i++) {
            if (arr[i] < root) left[lc++] = arr[i];
            else right[rc++] = arr[i];
        }

        int wL = slove(left, 0, lc);
        int wR = slove(right, 0, rc);

        long res = comb[n - 1][lc];
        res = (res * wL) % MOD;
        res = (res * wR) % MOD;
        return (int) res;
    }

    public static void buildComb() {
        for (int n = 0; n <= 20; n++) {
            comb[n][0] = comb[n][n] = 1;
            for (int i = 1; i < n; i++) {
                comb[n][i] = (comb[n - 1][i - 1] + comb[n - 1][i]) % MOD;
            }
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