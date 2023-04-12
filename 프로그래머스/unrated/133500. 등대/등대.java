import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	public static int[][] dp;
	public static boolean[] visit;
	public static ArrayList<Integer>[] g;
	
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        g = new ArrayList[n+1];
        visit = new boolean[n+1];
        dp = new int[n+1][2];
        
        for(int i = 0 ; i <= n ; i++){
            g[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < n - 1; i++){
        	g[lighthouse[i][0]].add(lighthouse[i][1]);
        	g[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        int[] arr = dfs(1);
        return Math.min(arr[0], arr[1]);
    }
    
//  0은 킨거 1은 안킨거
   public static int[] dfs(int v){
//    	System.out.println(v);
    	dp[v][0] = 1;
    	int[] temp = {0,0};
        visit[v] = true;
        
        for (int i = 0; i < g[v].size(); i++) {
			if(!visit[g[v].get(i)]) {
				temp = dfs(g[v].get(i));
				dp[v][0] += Math.min(temp[1] , temp[0]);
				dp[v][1] += temp[0];
			}
		}
    	return dp[v];
    }
}