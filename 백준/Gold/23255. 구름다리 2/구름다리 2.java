import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        int M = readInt();
        ArrayList<Integer>[] g = new ArrayList[N + 1];
        ArrayList<Integer>[] arr = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int v = readInt();
            int u = readInt();
            g[v].add(u);
            g[u].add(v);
        }

        for (int i = 1; i <= N; i++) {
            int cc = 1;
            Collections.sort(arr[i]);
            for (int j = 0; j < arr[i].size(); j++) {
                if(cc < arr[i].get(j)){
                    break;
                } else if(cc == arr[i].get(j)){
                    cc++;
                }
            }
            sb.append(cc).append(' ');
            for (int j = 0; j < g[i].size(); j++) {
                arr[g[i].get(j)].add(cc);
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
}