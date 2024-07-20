class Main {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        int[] arr = new int[N + 1];
        for(int i = 1 ; i <= N ; i++){
            arr[i] = readInt();
        }

        Seg seg = new Seg(N);
        for(int i = 1 ; i <= N ; i++){
            seg.update_range(1 , 1 , N , i , i , i - 1);
        }

        for(int i = 1 ; i <= N ; i++){
            seg.update_range(1 , 1 , N , 1 , arr[i] , 1);
        }

        int q = readInt();
        if(seg.query(1,1,N,1, N) >= N){
            sb.append("TAK");
        } else{
            sb.append("NIE");
        }
        sb.append('\n');

        while(q-->0){
            int idx = readInt();
            int v = readInt();
            seg.update_range(1 , 1 , N , 1 , arr[idx] , -1);
            seg.update_range(1 , 1 , N , 1 , v , 1);
            arr[idx] = v;
            if(seg.query(1,1,N,1, N) >= N){
                sb.append("TAK");
            } else{
                sb.append("NIE");
            }
            sb.append('\n');
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


class Seg{
    int[] tree;
    int[] lazy;

    Seg(int N){
        tree = new int[N << 2];
        lazy = new int[N << 2];
    }

    void update_range(int node , int s , int e , int l , int r , int v){
        update_lazy(node , s , e);
        int next = node << 1;
        int m = (s + e) >> 1;

        if(l > e || r < s){
            return;
        } else if(l <= s && r >= e){
            tree[node] += v;
            if(s != e){
                lazy[next] += v;
                lazy[next + 1] += v;
            }
            return;
        }

        update_range(next , s , m , l , r , v);
        update_range(next + 1 , m + 1 , e , l , r , v);
        tree[node] = Math.min(tree[next] , tree[next + 1]);
    }

    void update_lazy(int node , int s , int e){
        if(lazy[node] != 0){
            tree[node] += lazy[node];
            if(s != e) {
                lazy[node << 1] += lazy[node];
                lazy[(node << 1) + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    int query(int node , int s , int e , int l , int r){
        update_lazy(node , s , e);

        if(l > e || r < s){
            return 200000000;
        } else if(l <= s && r >= e){
            return tree[node];
        } else{
            int next = node << 1;
            int m = (s + e) >> 1;
            return Math.min(query(next , s , m , l , r) , query(next + 1 , m + 1 , e , l , r));
        }
    }
}