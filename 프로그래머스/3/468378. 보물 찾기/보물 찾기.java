import java.util.function.Function;
import java.util.*;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        
        int N = depth.length;
        int[][] dp = new int[N][N];
        
        for(int s = 0 ; s < N ; s++){
            for(int l = 0 ; l < N ; l++){
                int r = l + s;
                if(N <= r)
                    continue;
                dp[l][r] = 100000000;

                for(int m = l ; m <= r ; m++){
                    int t = depth[m];
                    
                    int lv = 0;
                    int rv = 0;
                    if(l < m)
                        lv += dp[l][m - 1];
                    if(m < r)
                        rv += dp[m + 1][r];
                    t += Math.max(lv , rv);
                    dp[l][r] = Math.min(dp[l][r] , t);
                }
            }
        }
        int res = 0; 
        
        int l = 0;
        int r = N - 1;
        while(l <= r){
            for(int m = 0 ; m < N ; m++){
                int t = depth[m];
                int lv = 0;
                int rv = 0;
                if(l < m)
                    lv = dp[l][m - 1];
                if(m < r)
                    rv = dp[m + 1][r];
                t += Math.max(lv , rv);
                
                if(dp[l][r] == t){
                    int q = excavate.apply(m + 1);
                    if(q == 0)
                        return m + 1;
                    if(q == -1)
                        r = m - 1;
                    else
                        l = m + 1;
                }
            } 
        }
        return res;
    }
}