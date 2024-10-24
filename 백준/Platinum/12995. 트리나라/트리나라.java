import java.util.*;

class Main {
    public static long[][] dp;
    public static ArrayList<Integer>[] g;
    public static long res = 0;
    public static int N , K;
    public static void main(String[] args) throws Exception {
        N = readInt();
        K = readInt();
        dp = new long[N + 1][K + 1];
        g = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int u = readInt();
            int v = readInt();
            g[u].add(v);
            g[v].add(u);
        }
        dfs(1,1);
        System.out.println(res % 1000000007);
    }
    public static void dfs(int v , int pa){
        dp[v][1] = 1;
        for (int i = 0; i < g[v].size(); i++) {
            int nv = g[v].get(i);
            if(nv != pa){
                dfs(nv , v);
                for (int j = K; j > 1; j--) {
                    for (int k = 1; k < j; k++) {
                        dp[v][j] += dp[nv][j - k] * dp[v][k];
                        dp[v][j] %= 1000000007;
                    }
                }
            }
        }
        res += dp[v][K];
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