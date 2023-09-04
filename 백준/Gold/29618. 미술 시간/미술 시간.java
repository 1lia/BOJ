public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int Q = readInt();
        Pos[] query = new Pos[Q];
        SegmentTree seg = new SegmentTree(N);
        for (int i = 0; i < Q; i++) {
            query[i] = new Pos(readInt(),readInt(),readInt());
        }

        for (int i = Q - 1; i >= 0; i--) {
            seg.update_range(1,1,N,query[i].a,query[i].b,query[i].x);
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(seg.query(1,1,N,i,i)).append(' ');
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
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
           return val;
    }
}
class SegmentTree{
    private long[] tree;
    private long[] lazy;

    SegmentTree(int n){
        double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
        long treesize = Math.round(Math.pow(2, treeh));
        tree = new long[Math.toIntExact(treesize)];
        lazy = new long[Math.toIntExact(treesize)];

    }

    void update_range(int node , int start , int end , int left , int right , long value) {
        update_lazy(node , start , end);

        if (left > end || right < start) {
            return;
        } else if(left <= start && right >= end ) {
            tree[node] = value;

            if(start != end) {
                lazy[node << 1] = value;
                lazy[(node << 1) + 1] = value;
            }
            return;
        }
        update_range(node << 1 , start , (start + end) >> 1 , left , right , value);
        update_range((node << 1) + 1, ((start + end) >> 1) + 1 , end , left , right , value);
        tree[node] =  tree[node << 1] + tree[(node << 1) + 1];
    }

    void update_lazy(int node , int start , int end){
        if(lazy[node] != 0) {
            tree[node] = lazy[node];
            if(start != end) {
                lazy[node << 1] = lazy[node];
                lazy[(node << 1) + 1] = lazy[node];
            }
            lazy[node] = 0;
        }
    }

    long query(int node , int start , int end , int left , int right) {
        update_lazy(node , start , end);
        if (end < left || right < start)
            return 0;
        else if(left <= start && end <= right) {
            return tree[node];
        } else {
            return query(node << 1 , start , (start + end) >> 1 , left , right) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
        }
    }
}

class Pos{
    int a;
    int b;
    int x;
    public Pos(int a, int b, int x) {
        this.a = a;
        this.b = b;
        this.x = x;
    }
}