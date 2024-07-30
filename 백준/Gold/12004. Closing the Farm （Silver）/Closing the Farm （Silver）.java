import java.util.*;

class Main {
    public static ArrayList<Integer>[] g;
    public static int[] p;
    public static boolean[] used;
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        int M = readInt();
        g = new ArrayList[N + 1];
        p = new int[N+1];
        Arrays.fill(p , -1);
        used = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            int a = readInt();
            int b = readInt();
            g[a].add(b);
            g[b].add(a);
        }

        int[] query = new int[N];
        String[] res = new String[N];
        for (int i = N - 1; i >= 0; i--) {
            query[i] = readInt();
        }
        for (int i = 0; i < N; i++) {
            int v = query[i];
            used[v] = true;
            for (int j = 0; j < g[v].size(); j++) {
                int nv = g[v].get(j);
                if(used[nv]){
                    merge(v , nv);
                }
            }
            if(size(v) == i + 1){
                res[i] = "YES";
            } else{
                res[i] = "NO";
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            sb.append(res[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static int find(int n) {
        if(p[n] < 0)
            return n;
        return p[n] = find(p[n]);
    }

    public static void merge(int a , int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;

        if (size(a) > size(b)) {
            a ^= b; b ^= a; a ^= b;
        }

        p[b] += p[a];
        p[a] = b;
    }

    public static int size(int n) {
        return -p[find(n)];
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