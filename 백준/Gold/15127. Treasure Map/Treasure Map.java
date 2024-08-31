import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        ArrayList<Node>[] g = new ArrayList[N + 1];
        int[] gold = new int[N + 1];
        int[] mi = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        int last = 0;
        for (int i = 1; i <= N; i++) {
            gold[i] = readInt();
            mi[i] = readInt();
            if(gold[i] % mi[i] == 0)
                last = Math.max(last , gold[i] / mi[i]);
            else
                last = Math.max(last , gold[i] / mi[i] + 1);
        }
        for (int i = 0; i < M; i++) {
            int u = readInt();
            int v = readInt();
            int t = readInt();
            g[u].add(new Node(v , t));
            g[v].add(new Node(u , t));
        }
        int[][] dp = new int[last + 1][N + 1];
        int res = dp[1][1] = gold[1];

        for (int i = 1; i < last; i++) {
            for (int j = 1; j <= N; j++) {
                if(dp[i][j] != 0){
                    for (int k = 0; k < g[j].size(); k++) {
                        Node node = g[j].get(k);
                        int nt = i + node.t;
                        int v = node.v;
                        if(nt <= last){
                            int cost = Math.max(gold[v] - (mi[v] * (nt - 1)) , 0);
                            dp[nt][v] = Math.max(dp[nt][v] , dp[i][j] + cost);
                            res = Math.max(res , dp[nt][v]);
                        }
                    }
                }
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

    static class Node{
        int v;
        int t;

        public Node(int v, int t) {
            this.v = v;
            this.t = t;
        }
    }
}