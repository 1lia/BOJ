import java.util.*;

class Solution {
    public static int cnt;
    public static int[][] g;
    public static int[] size;
    public int solution(int k, int[] num, int[][] links) {
        int N = num.length;
        boolean[] check = new boolean[N];
        g = new int[N][2];
        size = new int[N];
        int root = -1;
        int s = 1;
        
        for(int i = 0 ; i < N ; i++){
            if(links[i][0] != -1){
                check[links[i][0]] = true;
            }
            if(links[i][1] != -1){
                check[links[i][1]] = true;
            }
            g[i][0] = links[i][0];
            g[i][1] = links[i][1];
        }
        
        for(int i = 0 ; i < N ; i++){
            size[i] = num[i];
            s = Math.max(s , num[i]);
            if(!check[i]){
                root = i;
            }
        }

        int e = 100000000;
        int m = 0;
        while(s < e){
            m = (s+e) >> 1;
            cnt = 0;
            
            dfs(root , m);
            
            if(cnt < k){
                e = m;
            } else{
                s = m + 1;
            }
        }
        return s;
    }
    
    public static int dfs(int v , int max){
        if(v == -1){
            return 0;
        }
        int l = dfs(g[v][0] , max);
        int r = dfs(g[v][1] , max);
        int val = size[v];
        
        if(l + r + val <= max){
            return l + r + val;
        }
        cnt++;
        if(Math.min(l , r) + val <= max){
            return Math.min(l , r) + val;
        }
        cnt++;
        return val;
    }
}