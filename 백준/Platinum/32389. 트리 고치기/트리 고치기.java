import java.util.*;

class Main {
    public static ArrayList<Integer>[] g;
    public static int[][] dp;
    public static boolean[] fail;
    public static int K;

    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        K = readInt();
        g = new ArrayList[N + 1];
        fail = new boolean[N + 1];
        dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int u = readInt();
            int v = readInt();
            g[u].add(v);
            g[v].add(u);
        }
        for (int i = 0; i < M; i++) {
            fail[readInt()] = true;
        }
        dfs(1, 1);
        int res = 0;
        for (int i = 0; i <= K; i++) {
            res = Math.max(res , dp[1][i]);
        }
        System.out.println(res);
    }

    public static int dfs(int v, int p) {
        int cnt = 0;
        for (int i = 0; i < g[v].size(); i++) {
            int nv = g[v].get(i);
            if (nv != p) {
                cnt += dfs(nv, v);
                for (int j = K; j >= 0; j--) {
                    for (int k = j; k >= 0; k--) {
                        dp[v][j] = Math.max(dp[v][j], dp[nv][j - k] + dp[v][k]);
                    }
                }
            }
        }

        if (fail[v]) {
            cnt = Math.min(K , cnt + 1);
            for (int i = cnt; i > 0; i--) {
                dp[v][i] = dp[v][i - 1] + 1;
            }
            dp[v][0] = 0;
        } else {
            cnt = Math.min(K , cnt);
            for (int i = cnt; i >= 0; i--) {
                dp[v][i]++;
            }
        }
        return cnt;
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