import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        int K = readInt();
        int H = readInt();
        int E = readInt();
        ArrayList<Node>[] g = new ArrayList[N + 1];
        int[] cut = new int[M];
        int[] dist = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int v = readInt();
            int u = readInt();
            int w = readInt();
            g[v].add(new Node(u,w,i));
            g[u].add(new Node(v,w,i));
        }

        int v = readInt();
        int cost = 0;
        for (int i = 1; i < K; i++) {
            int nv = readInt();
            for (int j = 0; j < g[v].size(); j++) {
                Node node = g[v].get(j);
                if(node.v == nv){
                    cut[node.idx] = cost + 1;
                    cost += node.cost;
                    v = node.v;
                    break;
                }
            }
        }
        Arrays.fill(dist , 100000000);
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[H] = 0;
        q.offer(new Node(H,0,0));
        while(!q.isEmpty()){
            Node node = q.poll();
            if(dist[node.v] != node.cost)
                continue;

            for (int i = 0; i < g[node.v].size(); i++) {
                Node next = g[node.v].get(i);
                if(!(cut[next.idx] != 0 && cut[next.idx] <= node.cost + next.cost) && dist[next.v] > node.cost + next.cost){
                    q.offer(new Node(next.v,node.cost + next.cost,0));
                    dist[next.v] = node.cost + next.cost;
                }
            }
        }
        if(dist[E] == 100000000)
            System.out.println(-1);
        else System.out.println(dist[E]);
    }

    static class Node implements Comparable<Node>{
        int v;
        int cost;
        int idx;

        public Node(int v, int cost, int idx) {
            this.v = v;
            this.cost = cost;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
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