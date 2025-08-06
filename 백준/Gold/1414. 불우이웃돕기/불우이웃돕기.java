import java.io.*;
import java.util.*;

class Main {
    public static int size;
    public static ArrayList<Node>[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        boolean[] visit = new boolean[N];
        g = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i].charAt(j) != '0'){
                    char c = arr[i].charAt(j);
                    int val = 0;
                    if('a' <= c && c <= 'z')
                        val = arr[i].charAt(j) - 'a' + 1;
                    if('A' <= c && c <= 'Z')
                        val = arr[i].charAt(j) - 'A' + 27;
                    res += val;
                    if(i != j){
                        g[i].add(new Node(j,val));
                        g[j].add(new Node(i,val));
                    }
                }
            }
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        int cnt = 1;
        visit[0] = true;
        for (int i = 0; i < g[0].size(); i++) {
            q.offer(g[0].get(i));
        }

        while(!q.isEmpty()){
            Node node = q.poll();
            if(!visit[node.v]){
                visit[node.v] = true;
                cnt++;
                res -= node.cost;
                for (int j = 0; j < g[node.v].size(); j++) {
                    q.offer(g[node.v].get(j));
                }
            }
        }
        if(cnt == N)
            System.out.println(res);
        else System.out.println(-1);
    }

    static class Node implements Comparable<Node>{
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}