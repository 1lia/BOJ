import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        Pos[] arr = new Pos[N];
        boolean[] visit = new boolean[N];
        int[] g = new int[N];

        long min = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            long t = readLong();
            min = Math.min(t , min);
            arr[i] = new Pos(i , t);
        }
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            g[arr[i].idx] = i;
            if(arr[i].idx == i){
                g[i] = -1;
            }
        }
        long res = 0;
        for (int i = 0; i < N; i++) {
            if(!visit[i] && g[i] != -1){
                int cnt = 1;
                int t = g[i];
                long sum = arr[t].v;
                long m = arr[t].v;
                visit[i] = true;
                while(!visit[t]){
                    visit[t] = true;
                    cnt++;
                    t = g[t];
                    sum += arr[t].v;
                    m = Math.min(m , arr[t].v);
                }
                res += Math.min(sum + ((cnt-2) * m) , m + sum + (min * (cnt + 1)));
            }
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
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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

class Pos implements Comparable<Pos> {
    int idx;
    long v;

    public Pos(int idx, long v) {
        this.idx = idx;
        this.v = v;
    }

    @Override
    public int compareTo(Pos o) {
        if(this.v > o.v){
            return 1;
        }
        return -1;
    }
}