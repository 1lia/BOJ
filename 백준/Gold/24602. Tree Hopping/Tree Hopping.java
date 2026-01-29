import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        while(T-->0){
            int N = readInt();
            LCA lca = new LCA(N);
            for (int i = 1; i < N; i++) {
                int u = readInt();
                int v = readInt();
                lca.putEdge(u,v);
            }
            lca.dfs(1,1,1);
            int v = readInt();
            int res = 1;
            for (int i = 1; i < N; i++) {
                int nv = readInt();
                int cnt = 0;
                int a = v;
                int b = nv;
                while(res == 1){
                    if(cnt > 3){
                        res = 0;
                        break;
                    }
                    if(a == b){
                        break;
                    }
                    cnt++;
                    if(lca.depth[a] > lca.depth[b]){
                        a = lca.parent[a];
                    } else if(lca.depth[a] < lca.depth[b]){
                        b = lca.parent[b];
                    } else if(lca.depth[a] == lca.depth[b]){
                        a = lca.parent[a];
                        b = lca.parent[b];
                        cnt++;
                    }
                }
                v = nv;
            }
            sb.append(res).append('\n');
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

class LCA{
    int N;
    boolean[] visited;
    int[] depth;
    int[] parent;
    ArrayList<Integer>[] tree;
    public LCA(int n) {
        N = n;
        visited = new boolean[N+1];
        depth = new int[N+1];
        parent = new int[N+1];
        tree = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
    }
    public void putEdge(int a , int b) {
        tree[a].add(b);
        tree[b].add(a);
    }

    public void dfs(int up , int node, int dep){
        if (visited[node])
            return;
        visited[node] = true;
        depth[node] = dep;
        parent[node] = up;
        for (int i = 0; i < tree[node].size(); i++){
            dfs(node, tree[node].get(i), dep+1);
        }
    }
}
