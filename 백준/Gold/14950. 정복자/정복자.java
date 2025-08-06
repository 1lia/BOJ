import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        int t = readInt();
        ArrayList<Node>[] g = new ArrayList[N + 1];
        boolean[] visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = readInt();
            int b = readInt();
            int cost = readInt();
            g[a].add(new Node(b,cost));
            g[b].add(new Node(a,cost));
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        int res = 0;
        int pu = 0;
        for (int i = 0; i < g[1].size(); i++) {
            q.offer(g[1].get(i));
        }
        visit[1] = true;
        while(!q.isEmpty()){
            Node node = q.poll();
            if(!visit[node.v]){
                visit[node.v] = true;
                res += node.cost + pu;
                pu += t;
                for (int i = 0; i < g[node.v].size(); i++) {
                    q.offer(g[node.v].get(i));
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
    static class Node implements Comparable<Node>{
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}