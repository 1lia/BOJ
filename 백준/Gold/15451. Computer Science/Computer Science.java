import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        PriorityQueue<Pos> q = new PriorityQueue<Pos>();
        int N = readInt();
        int K = readInt() - 1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr);
        int res = arr[K] - arr[0];
        int idx = K;

        for (int i = 1; i + K < N; i++) {
            int cost = arr[i + K] - arr[i];
            q.offer(new Pos(i , i + K , cost));

            while(!q.isEmpty() && idx < i){
                Pos p = q.poll();
                idx = Math.max(idx , p.r);
                res = Math.max(res , p.cost);
            }
        }

        while(!q.isEmpty() && idx < N - 1){
            Pos p = q.poll();
            idx = Math.max(idx , p.r);
            res = Math.max(res , p.cost);
        }
        System.out.println(res);
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
        int l;
        int r;
        int cost;

        public Pos(int l, int r, int cost) {
            this.l = l;
            this.r = r;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }
}