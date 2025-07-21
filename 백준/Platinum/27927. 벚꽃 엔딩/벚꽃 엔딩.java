import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        SegmentTree seg = new SegmentTree(N);
        int[][] arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
        }
        seg.init(arr , 1,1,N);

        int s = 1;
        int e = N;
        int cnt = 0;
        boolean flag;
        while(s < e){
            int m = (s + e + 1) >> 1;
            flag = false;
            for (int i = m; i <= N; i++) {
                int[] temp = seg.query(1,1,N,i - m + 1,i);
                if(temp[0] <= temp[1]){
                    s = m;
                    flag = true;
                    break;
                }
            }
            if(!flag)
                e = m - 1;
        }
        ArrayList<Pos> qq = new ArrayList<Pos>();
        for (int i = e; i <= N; i++) {
            int[] temp = seg.query(1,1,N,i - e + 1,i);
            if(temp[0] <= temp[1]){
                qq.add(new Pos(temp[0] , temp[1]));
            }
        }
        Collections.sort(qq);
        int ee = 0;
        for (int i = 0; i < qq.size(); i++) {
            int days = qq.get(i).e - Math.max(qq.get(i).s, ee + 1) + 1;
            if(days < 0)
                days = 0;
            cnt += days;

            ee = Math.max(ee , qq.get(i).e);
        }
        System.out.println(e + " " + cnt);
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

    static class Pos implements Comparable<Pos>{
        int s;
        int e;

        public Pos(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pos o) {
            return this.s - o.s;
        }
    }

    static class SegmentTree{
        private int[][] tree;

        SegmentTree(int n){
            double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
            long treesize = Math.round(Math.pow(2, treeh));
            tree = new int[Math.toIntExact(treesize)][2];
        }

        void init(int[][] arr , int node , int start , int end) {
            if(start == end){
                tree[node][0] = arr[start][0];
                tree[node][1] = arr[start][1];
            }

            else {
                int mid = (start + end) >> 1;
                int next = node << 1;
                init(arr , next , start , mid);
                init(arr , next + 1 , mid + 1 , end);
                tree[node][0] = Math.max(tree[next][0] , tree[next + 1][0]);
                tree[node][1] = Math.min(tree[next][1] , tree[next + 1][1]);
            }
        }
        int[] query(int node , int start , int end , int left , int right) {
            if (end < left || right < start)
                return new int[] {0,Integer.MAX_VALUE};
            else if(left <= start && end <= right) {
                return tree[node];
            } else {
                int mid = (start + end) >> 1;
                int next = node << 1;
                int[] temp = new int[2];
                int[] l = query(next , start , mid , left , right);
                int[] r = query(next + 1 , mid + 1 , end , left , right);
                temp[0] = Math.max(l[0] , r[0]);
                temp[1] = Math.min(l[1] , r[1]);
                return temp;
            }
        }
    }
}