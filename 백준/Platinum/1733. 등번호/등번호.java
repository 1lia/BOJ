import java.util.ArrayList;

public class Main {
    public static ArrayList<Integer>[] g;
    public static int[] match;
    public static int[] visit;
    public static void main(String[] args) throws Exception {
        int N = readInt();
        g = new ArrayList[1000001];
        visit = new int[1000001];
        match = new int[N+1];
        for (int i = 0; i <= 1000000; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            g[readInt()].add(i);
            g[readInt()].add(i);
        }

        int cnt = 0;
        for (int i = 1; i <= 1000000; i++) {
            if(dfs(i,i)){
               cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        if(cnt == N){
            for (int i = 1; i <= N; i++) {
                sb.append(match[i]).append('\n');
            }
            System.out.println(sb);
        } else{
            System.out.println(-1);
        }
    }
    public static boolean dfs(int v , int c){
        visit[v] = c;
        for (int i = 0; i < g[v].size(); i++) {
            int nxt = g[v].get(i);
            if(match[nxt] == 0 || (visit[match[nxt]] != c) && dfs(match[nxt] , c)){
                match[nxt] = v;
                return true;
            }
        }
        return false;
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
}