import java.util.*;

public class Main {
    static ArrayList<Integer>[] g;
    static int[] visit , in;
    static int res = 0;
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int M = readInt();
        g = new ArrayList[N];
        visit = new int[N];
        in = new int[N];
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int v = readInt();
            int u = readInt();
            g[v].add(u);
            in[u]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if(in[i] == 0){
                q.offer(i);
            }
        }
        int cnt = 0;
        while(!q.isEmpty()){
            int v = q.poll();
            cnt++;
            for (int i = 0; i < g[v].size(); i++) {
                int nv = g[v].get(i);
                in[nv]--;
                if(in[nv] == 0){
                    q.offer(nv);
                }
            }
        }
        if(cnt == N){
            System.out.println(0);
        }else System.out.println(1);
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