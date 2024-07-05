import java.util.*;

public class Main {
    public static ArrayList<Integer>[] g;
    public static long[] money , leadership;
    public static long res , M;
    public static void main(String[] args) throws Exception {
        int N = readInt();
        M = readLong();
        g = new ArrayList[N + 1];
        money = new long[N + 1];
        leadership = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N ; i++) {
            g[readInt()].add(i);
            money[i] = readLong();
            leadership[i] = readLong();
        }

        dfs(1);
        System.out.println(res);
    }

    public static Nin dfs(int v){
        if(g[v].size() == 0){
            Nin nin = new Nin(money[v]);
            nin.pq.offer(money[v]);
            res = Math.max(res, leadership[v]);
            return nin;
        }

        Nin base = dfs(g[v].get(0));
        for (int i = 1; i < g[v].size() ; i++) {
            int nv = g[v].get(i);
            Nin temp = dfs(nv);

            if(base.pq.size() >= temp.pq.size()){
                while(!temp.pq.isEmpty()){
                    long money = temp.pq.poll();
                    base.money += money;
                    base.pq.offer(money);
                    if(base.money > M){
                        base.money -= base.pq.poll();
                    }
                }
            } else{
                while(!base.pq.isEmpty()){
                    long money = base.pq.poll();
                    temp.money += money;
                    temp.pq.offer(money);
                    if(temp.money > M){
                        temp.money -= temp.pq.poll();
                    }
                }
                base = temp;
            }
        }
        base.money += money[v];
        base.pq.offer(money[v]);
        if(base.money >= M){
            base.money -= base.pq.poll();
        }
        res = Math.max(res , leadership[v] * base.pq.size());
        return base;
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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

class Nin{
    long money;
    PriorityQueue<Long> pq;

    public Nin(long money) {
        this.money = money;
        pq = new PriorityQueue<>(Collections.reverseOrder());
    }
}