import java.util.*;

class Main {
    public static int[] cost , parent;
    public static StringBuilder sb;
    public static ArrayList<Integer>[] g;
    public static void main(String[] args) throws Exception{
        sb = new StringBuilder();
        int N = readInt();
        cost = new int[N + 1];
        parent = new int[N + 1];
        g = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList();
        }
        for (int i = 2; i <= N; i++) {
            g[readInt()].add(i);
        }
        for (int i = 1; i <= N; i++) {
            cost[i] = readInt();
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1,cost[1]));
        while(!q.isEmpty()){
            Node node = q.poll();
            for (int i = 0; i < g[node.v].size(); i++) {
                int v = g[node.v].get(i);
                q.offer(new Node(v , cost[v]));
            }
            sb.append(node.cost).append('\n');
        }
        System.out.println(sb);
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

class Node implements Comparable<Node>{
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