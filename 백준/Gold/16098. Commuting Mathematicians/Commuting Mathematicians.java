import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int N = readInt();
            int L = readInt();
            ArrayList<Node>[] g = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                g[i] = new ArrayList<>();
            }
            for (int i = 0; i < L; i++) {
                int c = readInt();
                int v = readInt();
                for (int j = 1; j < c; j++) {
                    int cost = readInt();
                    int nv = readInt();
                    g[v].add(new Node(nv,cost,i,0));
                    g[nv].add(new Node(v,cost,i,0));
                    v = nv;
                }
            }
            int s = readInt();
            int e = readInt();
            PriorityQueue<Node> q = new PriorityQueue<>();
            int[][] visit = new int[L][N];
            int[][] cnt = new int[L][N];
            for (int i = 0; i < L; i++) {
                Arrays.fill(visit[i] , Integer.MAX_VALUE);
                visit[i][s] = 0;
            }
            q.offer(new Node(s,0,-1,-1));
            while(!q.isEmpty()){
                Node node = q.poll();
                if(node.v == e) {
                    sb.append(node.cost).append(' ').append(node.cnt).append('\n');
                    break;
                }
                if(node.color >= 0 && (visit[node.color][node.v] < node.cost || cnt[node.color][node.v] < node.cnt))
                    continue;
                for (int i = 0; i < g[node.v].size(); i++) {
                    Node next = g[node.v].get(i);
                    Node t = new Node(next.v,node.cost + next.cost,next.color,node.cnt);
                    if(node.color != next.color){
                        t.cnt++;
                    }
                    if(next.cost + node.cost < visit[next.color][next.v]){
                        q.offer(t);
                        cnt[next.color][next.v] = t.cnt;
                        visit[next.color][next.v] = next.cost + node.cost;
                    }
                    if(next.cost + node.cost == visit[next.color][next.v] && cnt[next.color][next.v] > t.cnt){
                        cnt[next.color][next.v] = t.cnt;
                        q.offer(t);
                    }
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }
    static class Node implements Comparable<Node>{
        int v;
        int cost;
        int color;
        int cnt;

        public Node(int v, int cost, int color, int cnt) {
            this.v = v;
            this.cost = cost;
            this.color = color;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost == o.cost) {
                return this.cnt - o.cnt;
            }
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