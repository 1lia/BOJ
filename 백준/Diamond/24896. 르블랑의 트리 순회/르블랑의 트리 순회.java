import java.util.*;

public class Main {
    static ArrayList<Integer>[] g;
    static int[][] dp;
    static int[] cnt;
    static int[] cnt2;
    static boolean[] check;
    public static void main(String[] args) throws Exception {
        int N = readInt();
        dp = new int[N + 1][3];
        cnt = new int[N + 1];
        cnt2 = new int[N + 1];
        g = new ArrayList[N + 1];
        check = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
            dp[i][2] = -1;
        }

        for (int i = 1; i < N; i++) {
            int v = readInt();
            int u = readInt();
            g[v].add(u);
            g[u].add(v);
        }
        dfs(1,1);
        dfs2(1,1);

        for (int i = 1; i <= N; i++) {
            if(cnt[i] < 3){
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    public static void dfs2(int v , int p){
        int t = Math.max(dp[p][0] , dp[p][1] + 1);
        if(v != 1){
            if(cnt[v] == dp[p][0]){
                t = Math.max(dp[p][1] , dp[p][2] + 1);
            } else if(cnt[v] == dp[p][1]){
                t = Math.max(dp[p][0] , dp[p][2] + 1);
            }
            swap(v , t);

            cnt[v] = Math.max(dp[v][0] , dp[v][1] + 1);
        }

        for (int i = 0; i < g[v].size(); i++) {
            int nv = g[v].get(i);
            if(p != nv){
                dfs2(nv , v);
            }
        }
    }

    public static int dfs(int v , int p){
        for (int i = 0; i < g[v].size(); i++) {
            int nv = g[v].get(i);
            if(p != nv){
                int t = dfs(nv,v);
                swap(v , t);
            }
        }
        return cnt[v] = Math.max(dp[v][0] , dp[v][1] + 1);
    }

    public static void swap(int v , int t){
        if(dp[v][2] < t)
            dp[v][2] = t;
        if(dp[v][1] < dp[v][2]){
            int tt = dp[v][1];
            dp[v][1] = dp[v][2];
            dp[v][2] = tt;
        }
        if(dp[v][0] < dp[v][1]){
            int tt = dp[v][0];
            dp[v][0] = dp[v][1];
            dp[v][1] = tt;
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