import java.util.ArrayList;

public class Main {
    public static ArrayList<Integer>[] g;
    public static int[] num;
    public static int cnt;
    public static Pos[] et;
    public static long[] depth;
    public static boolean[] visit;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        int C = readInt();
        g = new ArrayList[N+1];
        num = new int[N+1];
        et = new Pos[N+1];
        depth = new long[N+1];
        visit = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int a = readInt();
            int b = readInt();
            g[a].add(b);
            g[b].add(a);
        }

        dfs(C , 1L);
        SegmentTree seg = new SegmentTree(N);
        int M = readInt();
        int v = 0;
        for (int i = 0; i < M; i++) {
            int t = readInt();
            v = num[readInt()];
            if(t == 1) {
                seg.update(1,1,N,v,1L);
            } else {
                sb.append(seg.query(1, 1, N, et[v].s, et[v].e) * depth[v]).append('\n');
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
    public static void dfs(int v , long dep){
        visit[v] = true;
        cnt++;
        num[v]=cnt;
        int t=cnt;
        et[t] = new Pos();
        et[t].s=cnt;
        depth[cnt] = dep;
        for (int i = 0; i < g[v].size(); i++) {
            int next = g[v].get(i);
            if(!visit[next])
                dfs(next , dep + 1);
        }
        et[t].e=cnt;
    }
}
class Pos{
    public int s;
    public int e;
}

class SegmentTree{
    private long[] tree;

    SegmentTree(int n){
        double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
        long treesize = Math.round(Math.pow(2, treeh));
        tree = new long[Math.toIntExact(treesize)];
    }

    void update(int node , int start , int end , int index , long value) {
        if(index < start || end < index)
            return;

        if(index == start && end == index) {
            tree[node] += value;
        } else {
            update(node << 1 , start , (start + end) >> 1 , index , value);
            update((node << 1) + 1 , ((start + end) >> 1) + 1 , end , index , value);
            tree[node] = tree[node << 1] + tree[(node << 1) + 1];
        }
    }

    long query(int node , int start , int end , int left , int right) {
        if (end < left || right < start)
            return 0;
        else if(left <= start && end <= right) {
            return tree[node];
        } else {
            return query(node << 1 , start , (start + end) >> 1 , left , right) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
        }
    }
}