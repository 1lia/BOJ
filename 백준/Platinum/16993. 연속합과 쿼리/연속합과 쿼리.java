class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        Seg seg = new Seg(N);
        for (int i = 1; i <= N; i++) {
            seg.arr[i] = readInt();
        }
        seg.init(1,1,N);
        int M = readInt();
        for (int i = 0; i < M; i++) {
            sb.append(seg.query(1,1,N,readInt(),readInt()).max).append('\n');
        }
        System.out.print(sb);
    }
    static class Seg{
        Node[] tree;
        long[] arr;

        public Seg(int N) {
            tree = new Node[N << 2];
            arr = new long[N + 1];
        }

        public void init(int node , int start , int end){
            if(start == end){
                tree[node] = new Node(arr[start],arr[start],arr[start],arr[start]);
                return;
            }
            int next = node << 1;
            int mid = (start + end) >> 1;
            init(next,start,mid);
            init(next + 1,mid + 1,end);
            tree[node] = merge(tree[next] , tree[next + 1]);
        }

        public Node query(int node , int start , int end , int left , int right){
            if(right < start || end < left)
                return null;
            if(left <= start && end <= right){
                return tree[node];
            }
            int next = node << 1;
            int mid = (start + end) >> 1;
            Node l = query(next,start,mid,left,right);
            Node r = query(next + 1,mid + 1,end,left,right);
            return merge(l,r);
        }

        private Node merge(Node l, Node r) {
            if(l == null)
                return r;
            if(r == null)
                return l;
            Node node = new Node(0,0,0,0);
            node.l = Math.max(l.l , l.sum + r.l);
            node.r = Math.max(r.r , r.sum + l.r);
            node.sum = l.sum + r.sum;
            node.max = Math.max(l.max , Math.max(r.max , l.r + r.l));
            return node;
        }

        class Node{
            long l;
            long r;
            long sum;
            long max;

            public Node(long l, long r, long sum, long max) {
                this.l = l;
                this.r = r;
                this.sum = sum;
                this.max = max;
            }
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