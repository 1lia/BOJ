import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        int X = readInt();
        int[] res = new int[N + 1];
        ArrayList<Node>[] g = new ArrayList[N + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int v = readInt();
            int u = readInt();
            int time = readInt();
            int cost = readInt();
            g[v].add(new Node(u , time , cost , 0));
            g[u].add(new Node(v , time , cost , 0));
        }
        Arrays.fill(res , Integer.MAX_VALUE);
        res[1] = 0;
        q.offer(new Node(1,0,Integer.MAX_VALUE , 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            for (int i = 0; i < g[node.v].size(); i++) {
                Node next = g[node.v].get(i);
                int cost = next.time + node.time + X / Math.min(next.cost , node.cost);
                if(cost < res[next.v]){
                    res[next.v] = cost;
                    q.offer(new Node(next.v , next.time + node.time , Math.min(next.cost , node.cost) , cost));
                }
            }
        }
        System.out.println(res[N]);
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
    int time;
    int cost;
    int all;

    public Node(int v, int time, int cost , int all) {
        this.v = v;
        this.time = time;
        this.cost = cost;
        this.all = all;
    }

    @Override
    public int compareTo(Node o) {
        return this.all - o.all;
    }
}