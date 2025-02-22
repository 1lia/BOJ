import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        PriorityQueue<Node> q = new PriorityQueue<>();
        int N = readInt();
        int M = readInt();
        int K = readInt();
        long[][] dp = new long[N + 1][K + 1];
        ArrayList<Node>[] g = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i] ,10000000000000L);
        }
        dp[1][0] = 0;
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            int v = readInt();
            int u = readInt();
            long cost = readLong();
            g[v].add(new Node(u , cost , 0));
            g[u].add(new Node(v , cost , 0));
        }
        q.offer(new Node(1,0,0));
        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.v == N)
                break;

            if(dp[node.v][node.cnt] != node.cost)
                continue;

            for (int i = 0; i < g[node.v].size(); i++) {
                Node next = g[node.v].get(i);

                if(node.cost + next.cost < dp[next.v][node.cnt] ){
                    q.offer(new Node(next.v, node.cost + next.cost ,node.cnt));
                    dp[next.v][node.cnt] = node.cost + next.cost;
                }

                if(node.cnt < K && node.cost < dp[next.v][node.cnt + 1] ){
                    q.offer(new Node(next.v, node.cost ,node.cnt + 1));
                    dp[next.v][node.cnt + 1] = node.cost;
                }
            }
        }
        long res = dp[N][0];
        for (int i = 1; i <= K; i++) {
            res = Math.min(res , dp[N][i]);
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

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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

class Node implements Comparable<Node>{
    int v;
    long cost;
    int cnt;

    public Node(int v, long cost , int cnt) {
        this.v = v;
        this.cost = cost;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        if(o.cost > this.cost){
            return -1;
        } else if(o.cost < this.cost){
            return 1;
        }
        return 0;
    }
}