import java.util.*;

class Solution {
    static ArrayList<Integer>[] g;
    static Node[] arr;
    static int[][] answer;
    static int idx;
    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        g = new ArrayList[N + 1];
        arr = new Node[N];
        int max = -1;
        int x = 0;
        int v = 0;
        for(int i = 0 ; i < N ; i++){
            arr[i] = new Node(i + 1 , nodeinfo[i][0] , nodeinfo[i][1]);
        }
        Arrays.sort(arr);
        for(int i = 0 ; i < N ; i++){
            if(max < arr[i].y){
                max = arr[i].y;
                x = i;
                v = arr[i].v;
            }
        }
        for(int i = 0 ; i <= N ; i++){
            g[i] = new ArrayList();
        }

        dfs(v , 0 , x);
        dfs(v , x + 1 , N);
        answer = new int[2][N];
        System.out.println(v);
        preorder(v);
        idx = 0;
        postorder(v);
        return answer;
    }
    public static void dfs(int pa , int s , int e){
        if(s == e)
            return;
        int high = -1;
        int m = 0;
        int v = 0;
        for (int i = s; i < e; i++) {
            if(high < arr[i].y){
                high = arr[i].y;
                m = i;
                v = arr[i].v;
            }
        }
        if(v != 0){
            g[pa].add(v);
            dfs(v , s , m);
            dfs(v , m + 1 , e);
        }
    }

    public static void preorder(int v){
        answer[0][idx++] = v;
        for (int i = 0; i < g[v].size(); i++) {
            preorder(g[v].get(i));
        }
    }

    public static void postorder(int v){
        for (int i = 0; i < g[v].size(); i++) {
            postorder(g[v].get(i));
        }
        answer[1][idx++] = v;
    }

    static class Pos{
        int pa;
        int min;
        int max;
        int deep;

        public Pos(int pa, int min, int max, int deep) {
            this.pa = pa;
            this.min = min;
            this.max = max;
            this.deep = deep;
        }
    }

    static class Node implements Comparable<Node>{
        int v;
        int x;
        int y;

        public Node(int v, int x , int y) {
            this.v = v;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.x - o.x;
        }
    }
}