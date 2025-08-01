import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        HLD hld = new HLD(N);

        for (int i = 1; i < N; i++) {
            int u = readInt();
            int v = readInt();
            int w = readInt();
            hld.putEdge(i , u , v , w);
        }
        hld.run();

        int M = readInt();
        for (int i = 0; i < M; i++) {
            if(readInt() == 1) {
                hld.update(readInt(), readInt());
            } else {
                sb.append(hld.query(readInt(), readInt())).append('\n');
            }
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


class HLD{
    private ArrayList<Node>[] g;
    private ArrayList<Integer>[] tree;
    private boolean[] visit;
    private int[] cost;
    private int[] sz; //서브트리 크기
    private int[] in , out; //dfs order
    private int[] top; //속한 체인에서 가장 위의 정점
    private int[] dep; // 깊이
    private int[] par; // 부모
    private int[] edge;
    private int piv , N;
    private Seg seg;

    HLD(int n) {
        N = n;
        g = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        sz = new int[N + 1];
        in = new int[N + 1];
        out = new int[N + 1];
        top = new int[N + 1];
        cost = new int[N + 1];
        dep = new int[N + 1];
        edge = new int[N + 1];
        par = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
            g[i] = new ArrayList<>();
        }
        seg = new Seg(N);
    }

    public void putEdge(int idx , int v , int u , int w) {
        g[v].add(new Node(idx ,u , w));
        g[u].add(new Node(idx ,v , w));
    }

    public void run() {
        makeTree(1);
        sub(1);
        top[1] = 1;
        hld(1);
        for (int i = 1; i < N; i++) {
            update(i , cost[edge[i]]);
        }
    }

    public void update(int v , int val) {
        seg.update(1, 1, N, in[edge[v]], val);
    }

    public int query(int a , int b) {
        int res = 0;
        while(top[a] != top[b]) {
            if(dep[top[a]] < dep[top[b]]) {
                int z = a;
                a = b;
                b = z;
            }

            int st = top[a];
            res = Math.max(seg.query(1,1,N,in[st], in[a]) , res);
            a = par[st];
        }
        if(dep[a] > dep[b]) {
            int z = a;
            a = b;
            b = z;
        }
        res = Math.max(seg.query(1,1,N,in[a] + 1, in[b]) , res);
        return res;
    }

    private void makeTree(int v) {
        visit[v] = true;
        for (Node node : g[v]) {
            if(!visit[node.u]) {
                par[node.u] = v;
                dep[node.u] = dep[v] + 1;
                tree[v].add(node.u);
                cost[node.u] = node.w;
                edge[node.idx] = node.u;
                makeTree(node.u);
            }
        }
    }

    private void hld(int v) {
        in[v] = ++piv;
        for (int i = 0; i < tree[v].size(); i++) {
            int u = tree[v].get(i);

            if(i == 0) {
                top[u] = top[v];
            } else {
                top[u] = u;
            }
            hld(u);
        }
        out[v] = piv;
    }

    private void sub(int v) {
        sz[v] = 1;

        for (int i = 0; i < tree[v].size(); i++) {
            int u = tree[v].get(i);
            sub(u);
            sz[v] += sz[u];

            if(sz[u] > sz[tree[v].get(0)]) {
                swap(v,i,0);
            }
        }
    }

    private void swap(int v , int a , int b) {
        int t = tree[v].get(a);
        tree[v].set(a, tree[v].get(b));
        tree[v].set(b, t);
    }


    private class Seg{
        private int[] tree;
        Seg(int N){
            tree = new int[Math.toIntExact(Math.round(Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1)))];
        }

        void update(int node , int start , int end , int idx , int val) {
            if(idx < start || end < idx)
                return;

            if(idx == start && end == idx) {
                tree[node] = val;
            } else {
                int mid = (start + end) >> 1;
                int next = node << 1;
                update(next , start , mid , idx , val);
                update(next + 1 , mid + 1 , end , idx , val);
                tree[node] = Math.max(tree[next] , tree[next + 1]);
            }
        }

        int query(int node , int start , int end , int left , int right) {
            if (end < left || right < start)
                return 0;
            else if(left <= start && end <= right) {
                return tree[node];
            } else {
                int mid = (start + end) >> 1;
                int next = node << 1;
                return Math.max(query(next , start , mid , left , right) , query(next + 1 , mid + 1 , end , left , right));
            }
        }
    }

    private class Node{
        int idx;
        int u;
        int w;
        public Node(int idx, int u, int w) {
            this.idx = idx;
            this.u = u;
            this.w = w;
        }
    }
}