import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        Queue<Pos> q = new ArrayDeque<>();
        int T = readInt();
        while(T-->0){
            int N = readInt();
            int Q = (int) Math.pow(2 , N);
            int[] min = new int[Q + 1];
            int[] max = new int[Q + 1];
            int[] Query = new int[Q];

            for (int i = 1; i < Q; i++) {
                Query[i] = readInt();
                min[Query[i]]++;
            }
            for (int i = 1; i <= Q; i++) {
                min[i] = Q - (int) Math.pow(2 , min[i]) + 1;
            }
            q.offer(new Pos(1 , Q , 1));
            for (int i = Q - 1; i > 0 ; i--) {
                Pos pos = q.poll();
                int mid = (pos.l + pos.r) / 2;
                if(Query[i] <= mid){
                    q.offer(new Pos(mid + 1 , pos.r , pos.cost + 1));
                    q.offer(new Pos(pos.l , mid , pos.cost));
                } else{
                    q.offer(new Pos(mid + 1 , pos.r , pos.cost));
                    q.offer(new Pos(pos.l , mid , pos.cost + 1));
                }
            }
            while(!q.isEmpty()){
                Pos p = q.poll();
                max[p.l] = p.cost;
            }

            Q = readInt();
            for (int i = 0; i < Q; i++) {
                int idx = readInt();
                sb.append("Player ").append(idx).append(" can be ranked as high as ").append(max[idx]).append(" or as low as ").append(min[idx]).append(".\n");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static class Pos{
        int l;
        int r;
        int cost;

        public Pos(int l, int r, int cost) {
            this.l = l;
            this.r = r;
            this.cost = cost;
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