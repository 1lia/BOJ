import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        int[][][] dp = new int[N + 2][K + 1][K + 1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(0 < a && a <= N){
                arr[a]++; 
            }
            if(0 < b && b <= N){
                arr[b]--;
            }
        }

        for(int i = 1 ; i <= N ; i++){
            arr[i] += arr[i - 1];
        }
        
        for(int i = 1 ; i <= N ; i++){ 
            for(int j = 0 ; j <= K ; j++){ 
                Arrays.fill(dp[i][j] , -1);
            }
        }
        dp[1][K][0] = 0;
        int res = 0;
        for(int i = 1 ; i <= N ; i++){
            for(int j = K ; j >= 0 ; j--){
                for(int k = 0 ; k <= K ; k++){
                    if(dp[i][j][k] != -1){
                        if(arr[i] >= T){
                            dp[i + 1][j][0] = Math.max(dp[i][j][k] + 1 , dp[i + 1][j][0]);
                            res = Math.max(dp[i + 1][j][0] , res);
                        } else{
                            dp[i + 1][j][k] = Math.max(dp[i][j][k] , dp[i + 1][j][k]);
                            int cnt = Math.max(T - arr[i] - k , 0);
                            if(cnt <= j){
                                dp[i + 1][j - cnt][k + cnt] = Math.max(dp[i][j][k] + 1  , dp[i + 1][j - cnt][k + cnt]);
                                res = Math.max(dp[i + 1][j - cnt][k + cnt] , res);
                                dp[i + 1][j][k] = Math.max(dp[i][j][k] , dp[i + 1][j][k]); //친구추가 가능해도 안넣는경우 
                            }
                        }
                    }
                }   
            }
        }
        System.out.println(res);
    }
}