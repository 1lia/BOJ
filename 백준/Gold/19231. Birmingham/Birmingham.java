import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {
        Queue<Node> q = new ArrayDeque<>();
        int N = readInt();
        int M = readInt();
        int Q = readInt();
        int K = readInt();
        ArrayList<Integer>[] g = new ArrayList[N + 1];
        int[] res = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < Q; i++) {
            int idx = readInt();
            q.offer(new Node(idx , 0));
            visit[idx] = true;
        }

        for (int i = 0; i < M; i++) {
            int v = readInt();
            int u = readInt();
            g[v].add(u);
            g[u].add(v);
        }
        int size = K;
        int real = 1;
        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.time != 0){
                if(node.time > size){
                    real++;
                    size += (real * K);
                }
                res[node.v] = real;
            }

            for (int i = 0; i < g[node.v].size(); i++) {
                int nv = g[node.v].get(i);
                if(!visit[nv]){
                    q.offer(new Node(nv , node.time + 1));
                    visit[nv] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(res[i]).append(' ');
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

class Node{
    int v;
    int time;

    public Node(int v, int time) {
        this.v = v;
        this.time = time;
    }
}