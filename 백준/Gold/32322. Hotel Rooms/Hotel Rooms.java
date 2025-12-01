public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int t = readInt();
        StringBuilder sb = new StringBuilder();
        Seg seg = new Seg(N);

        for (int i = 0; i < t; i++) {
            if(readInt() == 17){
                int s = readInt();
                int e = readInt();
                sb.append(e - s + 1 - seg.query(1,1,N,s,e)).append('\n');
            } else{
                seg.update(1,1,N,readInt());
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

    static class Seg{
        int[] tree;
        Seg(int N){
            tree = new int[N << 2];
        }
        public void update(int node , int s , int e , int idx){
            if(idx < s || e < idx){
                return ;
            }
            if(s == e && idx == s){
                tree[node] = 1;
                return;
            }
            int m = (s + e) >> 1;
            int next = node << 1;
            update(next , s , m , idx);
            update(next + 1 , m + 1 , e , idx);
            tree[node] = tree[next] + tree[next + 1];
        }
        public int query(int node , int s , int e , int l , int r){
            if(e < l || r < s){
                return 0;
            }

            if(l <= s && e <= r){
                return tree[node];
            }

            int m = (s + e) >> 1;
            int next = node << 1;
            return query(next , s , m , l , r) + query(next + 1, m + 1 , e , l , r);
        }
    }
}
