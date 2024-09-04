import java.util.*;

class Main {
    public static ArrayList<Integer>[] g;
    public static void main(String[] args) throws Exception {
        int N = readInt();
        g = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList();
        }
        readInt();
        for (int i = 1; i < N; i++) {
            g[readInt()].add(i);
        }
        System.out.println(dfs(0));
    }

    public static int dfs(int v){
        int cost = 0;
        Integer[] arr = new Integer[g[v].size()];
        for (int i = 0; i < g[v].size() ; i++) {
            arr[i] = dfs(g[v].get(i));
        }
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            cost = Math.max(cost , arr[i] + i + 1);
        }
        return cost;
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