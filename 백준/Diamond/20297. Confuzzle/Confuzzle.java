import java.util.*;

public class Main {
    public static ArrayList<Integer>[] g;
    public static boolean[] visit;
    public static int[] num;
    public static int res = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        int N = readInt();
        num = new int[N + 1];
        visit = new boolean[N + 1];
        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
            num[i] = readInt();
        }

        for (int i = 1; i < N; i++) {
            int v = readInt();
            int u = readInt();
            g[v].add(u);
            g[u].add(v);
        }
        
        dfs(1 , 1);
        System.out.println(res);
    }

    public static Node dfs(int v , int depth) {
        visit[v] = true;
        Node base = new Node();
        base.map.put(num[v],depth);
        base.arr.add(num[v]);

        for (int z = 0; z < g[v].size(); z++) {
            int nv = g[v].get(z);
            if(!visit[nv]){
                Node node = dfs(nv , depth + 1);

                if(base.arr.size() < node.arr.size()){
                    Node temp = base;
                    base = node;
                    node = temp;
                }

                for (int i = 0; i < node.arr.size(); i++) {
                    int t = node.arr.get(i);
                    if(base.map.containsKey(t)){
                        int depth1 = base.map.get(t);
                        int depth2 = node.map.get(t);
                        res = Math.min(res , depth1 + depth2 - depth - depth);
                        base.map.put(t , Math.min(depth1 , depth2));
                    } else{
                        base.arr.add(t);
                        base.map.put(t , node.map.get(t));
                    }
                }
            }
        }
        return base;
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

class Node{
    ArrayList<Integer> arr;
    HashMap<Integer , Integer> map;

    public Node() {
        arr = new ArrayList<>();
        map = new HashMap<>();
    }
}